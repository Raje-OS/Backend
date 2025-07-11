package raje.com.rajebackend.content.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    public MoviesController(MovieQueryService queryService) {
        this.queryService = queryService;
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

    @GetMapping
    @Operation(summary = "Get all movies", description = "Retrieve all movies, optionally ordered by rating")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movies found"),
            @ApiResponse(responseCode = "404", description = "Movies not found")
    })
    public ResponseEntity<List<MovieResource>> getAllMovies(@RequestParam(required = false) String orderBy) {
        List<MovieResource> movies;

        if ("rating".equalsIgnoreCase(orderBy)) {
            movies = queryService.handle(new GetAllMoviesOrderedByRatingQuery()).stream()
                    .map(MovieResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();
        } else {
            movies = queryService.handle(new GetAllMoviesQuery()).stream()
                    .map(MovieResourceFromEntityAssembler::toResourceFromEntity)
                    .toList();
        }

        if (movies.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(movies);
    }
}
