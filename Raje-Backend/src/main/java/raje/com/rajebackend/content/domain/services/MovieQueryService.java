package raje.com.rajebackend.content.domain.services;

import raje.com.rajebackend.content.domain.model.aggregates.Movie;
import raje.com.rajebackend.content.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface MovieQueryService {
    List<Movie> handle(GetAllMoviesQuery query);
    Optional<Movie> handle(GetMovieByIdQuery query);
    List<Movie> handle(GetAllMoviesOrderedByRatingQuery query);
}
