package raje.com.rajebackend.person.domain.services;

import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.queries.GetActorByIdQuery;
import raje.com.rajebackend.person.domain.model.queries.GetActorsByIdsQuery;

import java.util.List;
import java.util.Optional;

public interface ActorQueryService {
    Optional<Actor> handle(GetActorByIdQuery query);
    List<Actor> handle(GetActorsByIdsQuery query);
}
