package raje.com.rajebackend.user.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;
import raje.com.rajebackend.user.domain.model.commands.CreateUserDetailsCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateUserDetailsCommand;
import raje.com.rajebackend.user.domain.services.UserDetailsCommandService;
import raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories.UserDetailsRepository;

import java.util.NoSuchElementException;

@Service
public class UserDetailsCommandServiceImpl implements UserDetailsCommandService {

    private final UserDetailsRepository repository;

    public UserDetailsCommandServiceImpl(UserDetailsRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails handle(CreateUserDetailsCommand command) {
        var userDetails = new UserDetails(command);
        return repository.save(userDetails);
    }

    @Override
    public UserDetails handle(String id, UpdateUserDetailsCommand command) {
        var userDetails = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserDetails with ID " + id + " not found"));
        userDetails.update(command);
        return repository.save(userDetails);
    }

    @Override
    public UserDetails handle(String id, UserDetails updatedDetails) {
        var existing = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("UserDetails with ID " + id + " not found"));

        existing.updateFrom(updatedDetails);
        return repository.save(existing);
    }

}
