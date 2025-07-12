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

    public UserCommandServiceImpl(UserRepository userRepository,
                                  HashingService hashingService,
                                  TokenService tokenService) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public User handle(SignUpCommand command) {
        if (userRepository.existsByEmail(command.email())) {
            throw new IllegalArgumentException("Email already exists: " + command.email());
        }

        String hashedPassword = hashingService.encode(command.password());

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

        return userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public User handle(SignInCommand command) {
        var user = userRepository.findByUserName(command.userName())
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + command.userName()));

        if (!hashingService.matches(command.password(), user.getPassword())) {
            throw new IllegalArgumentException("Invalid credentials");
        }

        String token = tokenService.generateToken(user.getUserName());
        System.out.println("Generated token: " + token);

        return user;
    }

    @Override
    @Transactional
    public User handle(String id, UpdateUserCommand command) {
        var user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));

        user.setFirstName(command.firstName());
        user.setLastName(command.lastName());
        user.setUserName(command.userName());
        user.setEmail(command.email());
        user.setImages(command.images());

        return userRepository.save(user);
    }

    @Override
    public String generateToken(String username) {
        return tokenService.generateToken(username);
    }

}
