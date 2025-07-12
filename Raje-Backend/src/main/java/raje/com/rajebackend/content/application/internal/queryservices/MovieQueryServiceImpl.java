package raje.com.rajebackend.content.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.content.domain.model.aggregates.Movie;
import raje.com.rajebackend.content.domain.model.queries.*;
import raje.com.rajebackend.content.domain.services.MovieQueryService;
import raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories.MovieRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieQueryServiceImpl implements MovieQueryService {

    // Repository for accessing Movie entities from the database
    private final MovieRepository movieRepository;

    // Constructor that injects the MovieRepository dependency
    public MovieQueryServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Handles the query to retrieve all movies.
     * @param query the query object (not used in this implementation)
     * @return a list of all Movie entities
     */
    @Override
    public List<Movie> handle(GetAllMoviesQuery query) {
        return movieRepository.findAll();
    }

    /**
     * Handles the query to retrieve a movie by its ID.
     * @param query contains the ID of the movie to retrieve
     * @return an Optional containing the Movie if found, or empty if not found
     */
    @Override
    public Optional<Movie> handle(GetMovieByIdQuery query) {
        return movieRepository.findById(query.id());
    }

    /**
     * Handles the query to retrieve all movies sorted by rating in descending order.
     * @param query the query object (not used in this implementation)
     * @return a list of movies sorted by their rating (highest first)
     */
    @Override
    public List<Movie> handle(GetAllMoviesOrderedByRatingQuery query) {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Movie::getCalificacion).reversed())
                .toList();
    }
}
