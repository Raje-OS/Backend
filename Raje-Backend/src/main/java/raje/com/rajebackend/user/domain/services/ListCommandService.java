package raje.com.rajebackend.user.domain.services;

import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;
import raje.com.rajebackend.user.domain.model.commands.CreateListCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateListCommand;

public interface ListCommandService {
    ListEntity handle(CreateListCommand command);
    ListEntity handle(String id, UpdateListCommand command);
    void handleDelete(String id);
}
