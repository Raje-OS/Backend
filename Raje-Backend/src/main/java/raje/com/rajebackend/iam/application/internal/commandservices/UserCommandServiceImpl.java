package raje.com.rajebackend.iam.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.iam.application.internal.outboundservices.hashing.HashingService;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.domain.model.commands.SignInCommand;
import raje.com.rajebackend.iam.domain.model.commands.SignUpCommand;
import raje.com.rajebackend.iam.domain.model.commands.UpdateUserCommand;
import raje.com.rajebackend.iam.domain.services.UserCommandService;
import raje.com.rajebackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    // Constructor with injected dependencies for repository, hashing, and token generation
    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    /**
     * Handles the sign-up logic for a new user.
     * Validates if the email is already registered and hashes the password before saving.
     *
     * @param command the SignUpCommand containing user registration data
     * @return the newly created User
     */
    @Override
    @Transactional
    public User handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Email already exists: " + command.email());
        }

        // Encode the password using the hashing service
        String hashedPassword = hashingService.encode(command.password());

        // Create the new User aggregate
        var user = new User(
                command.id(),
                command.firstName(),
                command.lastName(),
                command.userName(),
                command.email(),
                hashedPassword,
                command.platforms().stream().map(PlatformId::new).toList(),
                command.images()
        );

        // Persist and return the new user
        return userRepository.save(user);
    }

    /**
     * Handles user sign-in by verifying credentials.
     * Generates a token if authentication is successful.
     *
     * @param command the SignInCommand with username and password
     * @return the authenticated User
     */
    @Override
    @Transactional(readOnly = true)
    public User handle(SignInCommand command) {
        var user = userRepository.findByUserName(command.userName())
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + command.userName()));

        // Check password match
        if (!hashingService.matches(command.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        // Generate and log the token (for debugging or audit)
        String token = tokenService.generateToken(user.getUserName());
        System.out.println("Generated token: " + token);

        return user;
    }

    /**
     * Handles updating a user's information.
     *
     * @param id the ID of the user to update
     * @param command the update command with new data
     * @return the updated User
     */
    @Override
    @Transactional
    public User handle(String id, UpdateUserCommand command) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        // Update the user's information
        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setUserName(command.userName());
        user.setEmail(command.email());
        user.setImages(command.images());

        return userRepository.save(user);
    }

    /**
     * Generates a token for a given username.
     *
     * @param username the username to generate a token for
     * @return the JWT token
     */
    @Override
    public String generateToken(String username) {
        return tokenService.generateToken(username);
    }
}
