package raje.com.rajebackend.person.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.person.domain.model.aggregates.Author;
import raje.com.rajebackend.person.domain.model.commands.CreateAuthorCommand;
import raje.com.rajebackend.person.domain.services.AuthorCommandService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.AuthorRepository;

/**
 * Service implementation for handling author creation logic.
 */
@Service
public class AuthorCommandServiceImpl implements AuthorCommandService {

    private final AuthorRepository authorRepository;

    public AuthorCommandServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    /**
     * Handles the creation of a new author.
     *
     * @param command the command containing author data
     * @return the created Author entity
     */
    @Override
    @Transactional
    public Author handle(CreateAuthorCommand command) {
        Author author = new Author(command);
        return authorRepository.save(author);
    }
}
