package raje.com.rajebackend.content.domain.services;

import raje.com.rajebackend.content.domain.model.aggregates.Serie;
import raje.com.rajebackend.content.domain.model.queries.GetAllSeriesQuery;
import raje.com.rajebackend.content.domain.model.queries.GetSerieByIdQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllSeriesOrderedByRatingQuery;

import java.util.List;
import java.util.Optional;

public interface SerieQueryService {
    List<Serie> handle(GetAllSeriesQuery query);
    Optional<Serie> handle(GetSerieByIdQuery query);
    List<Serie> handle(GetAllSeriesOrderedByRatingQuery query);
}
