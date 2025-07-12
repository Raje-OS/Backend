package raje.com.rajebackend.user.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;
import raje.com.rajebackend.user.domain.model.commands.CreateListCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateListCommand;
import raje.com.rajebackend.user.domain.services.ListCommandService;
import raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories.ListRepository;

import java.util.NoSuchElementException;

/**
 * Service class that handles command operations for user lists,
 * including creation, update, and deletion.
 */
@Service
public class ListCommandServiceImpl implements ListCommandService {

    private final ListRepository listRepository;

    public ListCommandServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    /**
     * Handles the creation of a new list.
     *
     * @param command the command containing data to create the list
     * @return the newly created list entity
     */
    @Override
    public ListEntity handle(CreateListCommand command) {
        var list = new ListEntity(command);
        return listRepository.save(list);
    }

    /**
     * Handles the update of an existing list by ID.
     *
     * @param id      the ID of the list to update
     * @param command the command containing updated data
     * @return the updated list entity
     * @throws NoSuchElementException if the list with the given ID does not exist
     */
    @Override
    public ListEntity handle(String id, UpdateListCommand command) {
        var list = listRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("List with ID " + id + " not found"));

        list.update(command);
        return listRepository.save(list);
    }

    /**
     * Handles the deletion of a list by ID.
     *
     * @param id the ID of the list to delete
     */
    @Override
    public void handleDelete(String id) {
        listRepository.deleteById(id);
    }
}
