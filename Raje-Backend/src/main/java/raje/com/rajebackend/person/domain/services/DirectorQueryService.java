package raje.com.rajebackend.person.domain.services;

import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.domain.model.queries.GetDirectorByIdQuery;

import java.util.Optional;

public interface DirectorQueryService {
    Optional<Director> handle(GetDirectorByIdQuery  query);
}
