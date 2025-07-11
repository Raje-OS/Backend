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

    private final SerieRepository serieRepository;

    public SerieQueryServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Override
    public List<Serie> handle(GetAllSeriesQuery query) {
        return serieRepository.findAll();
    }

    @Override
    public Optional<Serie> handle(GetSerieByIdQuery query) {
        return serieRepository.findById(query.id());
    }

    @Override
    public List<Serie> handle(GetAllSeriesOrderedByRatingQuery query) {
        return serieRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Serie::getCalificacion).reversed())
                .toList();
    }
}
