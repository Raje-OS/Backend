package raje.com.rajebackend.person.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.domain.model.commands.CreateActorCommand;
import raje.com.rajebackend.person.domain.model.commands.CreateDirectorCommand;
import raje.com.rajebackend.person.domain.services.DirectorCommandService;

import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.DirectorRepository;

@Service
public class DirectorCommandServiceImpl implements DirectorCommandService {

    private final DirectorRepository directorRepository;

    public DirectorCommandServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Director handle(CreateDirectorCommand command) {
        Director director = new Director(command);
        return directorRepository.save(director);
    }
}
