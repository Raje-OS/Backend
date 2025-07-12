package raje.com.rajebackend.person.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.commands.CreateActorCommand;
import raje.com.rajebackend.person.domain.services.ActorCommandService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.ActorRepository;

/**
 * Service implementation for handling actor creation logic.
 */
@Service
public class ActorCommandServiceImpl implements ActorCommandService {

    private final ActorRepository actorRepository;

    public ActorCommandServiceImpl(ActorRepository actorRepository) {
        this.actorRepository = actorRepository;
    }

    /**
     * Handles the creation of a new actor.
     *
     * @param command the command containing actor data
     * @return the created Actor entity
     */
    @Override
    @Transactional
    public Actor handle(CreateActorCommand command) {
        Actor actor = new Actor(command);
        return actorRepository.save(actor);
    }
}
