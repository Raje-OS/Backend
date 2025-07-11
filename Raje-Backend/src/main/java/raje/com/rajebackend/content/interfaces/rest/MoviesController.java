package raje.com.rajebackend.content.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.content.application.internal.commandservices.UpdateMovieServiceImpl;
import raje.com.rajebackend.content.domain.model.commands.UpdateMovieCommand;
import raje.com.rajebackend.content.domain.model.queries.GetAllMoviesOrderedByRatingQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllMoviesQuery;
import raje.com.rajebackend.content.domain.model.queries.GetMovieByIdQuery;
import raje.com.rajebackend.content.domain.services.MovieQueryService;
import raje.com.rajebackend.content.interfaces.rest.resources.MovieResource;
import raje.com.rajebackend.content.interfaces.rest.transform.MovieResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/movies", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Movies", description = "Operations related to movies")
public class MoviesController {

    private final MovieQueryService queryService;
    private final UpdateMovieServiceImpl updateService;

    public MoviesController(MovieQueryService queryService, UpdateMovieServiceImpl updateService) {
        this.queryService = queryService;
        this.updateService = updateService;
    }

    @GetMapping
    @Operation(summary = "Get all movies", description = "Retrieve all movies without any specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies found"),
            @ApiResponse(responseCode = "404", description = "Movies not found")
    })
    public ResponseEntity<List<MovieResource>> getAllMovies() {
        var movies = queryService.handle(new GetAllMoviesQuery()).stream()
                .map(MovieResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        if (movies.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/ordered-by-rating")
    @Operation(summary = "Get all movies ordered by rating", description = "Retrieve all movies sorted by rating (desc)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies found"),
            @ApiResponse(responseCode = "404", description = "Movies not found")
    })
    public ResponseEntity<List<MovieResource>> getAllMoviesOrderedByRating() {
        var movies = queryService.handle(new GetAllMoviesOrderedByRatingQuery()).stream()
                .map(MovieResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        if (movies.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get movie by ID", description = "Retrieve a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    public ResponseEntity<MovieResource> getMovieById(@PathVariable String id) {
        var result = queryService.handle(new GetMovieByIdQuery(id));
        return result.map(movie ->
                        ResponseEntity.ok(MovieResourceFromEntityAssembler.toResourceFromEntity(movie)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a movie", description = "Update the information of a movie by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated successfully"),
            @ApiResponse(responseCode = "400", description = "ID mismatch in request"),
            @ApiResponse(responseCode = "404", description = "Movie not found")
    })
    public ResponseEntity<MovieResource> updateMovie(@PathVariable String id, @RequestBody UpdateMovieCommand command) {
        if (!id.equals(command.id())) {
            return ResponseEntity.badRequest().build();
        }

        var updated = updateService.handle(command);
        return ResponseEntity.ok(MovieResourceFromEntityAssembler.toResourceFromEntity(updated));
    }
}
