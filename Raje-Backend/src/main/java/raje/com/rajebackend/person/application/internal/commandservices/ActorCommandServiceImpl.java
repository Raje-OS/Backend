package raje.com.rajebackend.person.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.commands.CreateActorCommand;
import raje.com.rajebackend.person.domain.services.ActorCommandService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.ActorRepository;

@Service
public class ActorCommandServiceImpl implements ActorCommandService {

    private final ActorRepository actorRepository;

    public ActorCommandServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    @Override
    public Actor handle(CreateActorCommand command) {
        Actor actor = new Actor(command);
        return actorRepository.save(actor);
    }
}
