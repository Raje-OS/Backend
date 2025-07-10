package raje.com.rajebackend.person.domain.services;

import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.commands.CreateActorCommand;

public interface ActorCommandService {
    Actor handle(CreateActorCommand command);
}
