package raje.com.rajebackend.person.domain.services;

import raje.com.rajebackend.person.domain.model.aggregates.Author;
import raje.com.rajebackend.person.domain.model.queries.GetAuthorByIdQuery;

import java.util.Optional;

public interface AuthorQueryService {
    Optional<Author> handle(GetAuthorByIdQuery query);
}
