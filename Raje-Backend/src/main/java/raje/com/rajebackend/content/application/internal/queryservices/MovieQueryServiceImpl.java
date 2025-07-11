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

    private final MovieRepository movieRepository;

    public MovieQueryServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public List<Movie> handle(GetAllMoviesQuery query) {
        return movieRepository.findAll();
    }

    @Override
    public Optional<Movie> handle(GetMovieByIdQuery query) {
        return movieRepository.findById(query.id());
    }

    @Override
    public List<Movie> handle(GetAllMoviesOrderedByRatingQuery query) {
        return movieRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Movie::getCalificacion).reversed())
                .toList();
    }
}
