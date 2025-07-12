package raje.com.rajebackend.user.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;
import raje.com.rajebackend.user.domain.model.commands.CreateUserDetailsCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateUserDetailsCommand;
import raje.com.rajebackend.user.domain.services.UserDetailsCommandService;
import raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories.UserDetailsRepository;

import java.util.NoSuchElementException;

/**
 * Implementation of the {@link UserDetailsCommandService} interface.
 * Handles command operations for user detail records including creation,
 * update by command, and update by entity.
 */
@Service
public class UserDetailsCommandServiceImpl implements UserDetailsCommandService {

    private final UserDetailsRepository repository;

    /**
     * Constructs the service with a {@link UserDetailsRepository}.
     *
     * @param repository the repository used for persistence
     */
    public UserDetailsCommandServiceImpl(UserDetailsRepository repository) {
        this.repository = repository;
    }

    /**
     * Handles creation of a new {@link UserDetails} entity from a command.
     *
     * @param command the command containing data to create the user details
     * @return the newly created {@link UserDetails} entity
     */
    @Override
    public UserDetails handle(CreateUserDetailsCommand command) {
        var userDetails = new UserDetails(command);
        return repository.save(userDetails);
    }

    /**
     * Handles update of an existing {@link UserDetails} entity using a command.
     *
     * @param id      the ID of the user details to update
     * @param command the command containing the updated data
     * @return the updated {@link UserDetails} entity
     * @throws NoSuchElementException if no user details are found for the given ID
     */
    @Override
    public UserDetails handle(String id, UpdateUserDetailsCommand command) {
        var userDetails = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserDetails with ID " + id + " not found"));
        userDetails.update(command);
        return repository.save(userDetails);
    }

    /**
     * Handles update of an existing {@link UserDetails} entity using another {@link UserDetails} instance.
     *
     * @param id             the ID of the existing user details
     * @param updatedDetails the new {@link UserDetails} data to update with
     * @return the updated {@link UserDetails} entity
     * @throws NoSuchElementException if no user details are found for the given ID
     */
    @Override
    public UserDetails handle(String id, UserDetails updatedDetails) {
        var existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserDetails with ID " + id + " not found"));

        existing.updateFrom(updatedDetails);
        return repository.save(existing);
    }
}
