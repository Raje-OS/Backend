package raje.com.rajebackend.content.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.content.domain.model.aggregates.Serie;
import raje.com.rajebackend.content.domain.model.queries.GetAllSeriesQuery;
import raje.com.rajebackend.content.domain.model.queries.GetSerieByIdQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllSeriesOrderedByRatingQuery;
import raje.com.rajebackend.content.domain.services.SerieQueryService;
import raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories.SerieRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SerieQueryServiceImpl implements SerieQueryService {

    // Repository used to access Serie entities from the database
    private final SerieRepository serieRepository;

    // Constructor that injects the SerieRepository dependency
    public SerieQueryServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    /**
     * Handles the query to retrieve all series.
     * @param query the query object (not used in this implementation)
     * @return a list of all Serie entities
     */
    @Override
    public List<Serie> handle(GetAllSeriesQuery query) {
        return serieRepository.findAll();
    }

    /**
     * Handles the query to retrieve a single serie by its ID.
     * @param query contains the ID of the serie to retrieve
     * @return an Optional containing the Serie if found, or empty if not found
     */
    @Override
    public Optional<Serie> handle(GetSerieByIdQuery query) {
        return serieRepository.findById(query.id());
    }

    /**
     * Handles the query to retrieve all series sorted by rating in descending order.
     * @param query the query object (not used in this implementation)
     * @return a list of series sorted by their rating (highest first)
     */
    @Override
    public List<Serie> handle(GetAllSeriesOrderedByRatingQuery query) {
        return serieRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Serie::getCalificacion).reversed())
                .toList();
    }
}
