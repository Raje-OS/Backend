package raje.com.rajebackend.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.domain.model.queries.GetAllUsersQuery;
import raje.com.rajebackend.iam.domain.model.queries.GetUserByIdQuery;
import raje.com.rajebackend.iam.domain.services.UserQueryService;
import raje.com.rajebackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    // Constructor injecting the UserRepository dependency
    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Handles the query to retrieve all users from the database.
     * @param query the query object (not used in this implementation)
     * @return a list of all User entities
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    /**
     * Handles the query to retrieve a user by their unique ID.
     * @param query contains the ID of the user to be retrieved
     * @return an Optional containing the User if found, or empty if not found
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }
}
