package raje.com.rajebackend.content.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.content.application.internal.commandservices.UpdateSerieServiceImpl;
import raje.com.rajebackend.content.domain.model.commands.UpdateSerieCommand;
import raje.com.rajebackend.content.domain.model.queries.GetAllSeriesOrderedByRatingQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllSeriesQuery;
import raje.com.rajebackend.content.domain.model.queries.GetSerieByIdQuery;
import raje.com.rajebackend.content.domain.services.SerieQueryService;
import raje.com.rajebackend.content.interfaces.rest.resources.SerieResource;
import raje.com.rajebackend.content.interfaces.rest.transform.SerieResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/series", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Series", description = "Operations related to series")
public class SeriesController {

    private final SerieQueryService queryService;
    private final UpdateSerieServiceImpl updateService;

    // Constructor injecting query and update services
    public SeriesController(SerieQueryService queryService, UpdateSerieServiceImpl updateService) {
        this.queryService = queryService;
        this.updateService = updateService;
    }

    /**
     * Endpoint to retrieve all series without any specific order.
     * @return 200 OK with the list of series, or 404 if none are found
     */
    @GetMapping
    @Operation(summary = "Get all series", description = "Retrieve all series without any specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Series found"),
            @ApiResponse(responseCode = "404", description = "Series not found")
    })
    public ResponseEntity<List<SerieResource>> getAllSeries() {
        var series = queryService.handle(new GetAllSeriesQuery()).stream()
                .map(SerieResourceFromEntityAssembler::toResourceFromEntity) // Convert domain entities to resources
                .toList();

        if (series.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(series);
    }

    /**
     * Endpoint to retrieve all series sorted by rating in descending order.
     * @return 200 OK with sorted list of series, or 404 if none are found
     */
    @GetMapping("/ordered-by-rating")
    @Operation(summary = "Get all series ordered by rating", description = "Retrieve all series sorted by rating (desc)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Series found"),
            @ApiResponse(responseCode = "404", description = "Series not found")
    })
    public ResponseEntity<List<SerieResource>> getAllSeriesOrderedByRating() {
        var series = queryService.handle(new GetAllSeriesOrderedByRatingQuery()).stream()
                .map(SerieResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        if (series.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(series);
    }

    /**
     * Endpoint to retrieve a specific series by its ID.
     * @param id the unique identifier of the series
     * @return 200 OK with the series, or 404 if not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get series by ID", description = "Retrieve a series by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie found"),
            @ApiResponse(responseCode = "404", description = "Serie not found")
    })
    public ResponseEntity<SerieResource> getSerieById(@PathVariable String id) {
        var result = queryService.handle(new GetSerieByIdQuery(id));
        return result.map(serie ->
                        ResponseEntity.ok(SerieResourceFromEntityAssembler.toResourceFromEntity(serie)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to update a series.
     * Validates that the ID in the path matches the ID in the request body.
     * @param id the series ID from the path
     * @param command the update command containing new data
     * @return 200 OK with updated series, 400 if ID mismatch, or 404 if not found
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a series", description = "Update the information of a series by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Serie updated successfully"),
            @ApiResponse(responseCode = "400", description = "ID mismatch in request"),
            @ApiResponse(responseCode = "404", description = "Serie not found")
    })
    public ResponseEntity<SerieResource> updateSerie(@PathVariable String id, @RequestBody UpdateSerieCommand command) {
        if (!id.equals(command.id())) {
            return ResponseEntity.badRequest().build(); // Return 400 if path and body IDs do not match
        }

        var updated = updateService.handle(command); // Perform update operation
        return ResponseEntity.ok(SerieResourceFromEntityAssembler.toResourceFromEntity(updated));
    }
}
