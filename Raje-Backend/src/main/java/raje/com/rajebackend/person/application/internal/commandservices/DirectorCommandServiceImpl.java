package raje.com.rajebackend.person.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.domain.model.commands.CreateDirectorCommand;
import raje.com.rajebackend.person.domain.services.DirectorCommandService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.DirectorRepository;

/**
 * Service implementation for handling director creation logic.
 */
@Service
public class DirectorCommandServiceImpl implements DirectorCommandService {

    private final DirectorRepository directorRepository;

    public DirectorCommandServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    /**
     * Handles the creation of a new director.
     *
     * @param command the command containing director data
     * @return the created Director entity
     */
    @Override
    @Transactional
    public Director handle(CreateDirectorCommand command) {
        Director director = new Director(command);
        return directorRepository.save(director);
    }
}
