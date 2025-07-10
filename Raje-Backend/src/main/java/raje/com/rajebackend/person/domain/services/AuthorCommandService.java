package raje.com.rajebackend.person.domain.services;

import raje.com.rajebackend.person.domain.model.aggregates.Author;
import raje.com.rajebackend.person.domain.model.commands.CreateAuthorCommand;

public interface AuthorCommandService {
    Author handle(CreateAuthorCommand command);
}
