package raje.com.rajebackend.person.domain.services;

import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.domain.model.commands.CreateDirectorCommand;

public interface DirectorCommandService {
    Director handle(CreateDirectorCommand command);
}
