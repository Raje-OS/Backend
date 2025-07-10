package raje.com.rajebackend.person.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.queries.GetActorByIdQuery;
import raje.com.rajebackend.person.domain.model.queries.GetActorsByIdsQuery;
import raje.com.rajebackend.person.domain.services.ActorQueryService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.ActorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ActorQueryServiceImpl implements ActorQueryService {

    private final ActorRepository actorRepository;

    public ActorQueryServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Optional<Actor> handle(GetActorByIdQuery query) {
        return actorRepository.findById(query.id());
    }

    @Override
    public List<Actor> handle(GetActorsByIdsQuery query) {
        return actorRepository.findAllById(query.ids());
    }
}
