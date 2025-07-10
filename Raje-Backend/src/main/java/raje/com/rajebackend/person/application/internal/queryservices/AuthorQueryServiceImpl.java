package raje.com.rajebackend.person.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.person.domain.model.aggregates.Author;
import raje.com.rajebackend.person.domain.model.queries.GetAuthorByIdQuery;
import raje.com.rajebackend.person.domain.services.AuthorQueryService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.AuthorRepository;

import java.util.Optional;
@Service
public class AuthorQueryServiceImpl implements AuthorQueryService {
    private final AuthorRepository authorRepository;

    public AuthorQueryServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Optional<Author> handle(GetAuthorByIdQuery query) {
        return authorRepository.findById(query.id());
    }
}
