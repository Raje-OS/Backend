package raje.com.rajebackend.platform.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.iam.application.internal.outboundservices.hashing.HashingService;
import raje.com.rajebackend.platform.domain.model.aggregates.Platform;
import raje.com.rajebackend.platform.domain.model.commands.SignInPlatformCommand;
import raje.com.rajebackend.platform.domain.model.commands.SignUpPlatformCommand;
import raje.com.rajebackend.platform.domain.services.PlatformCommandService;
import raje.com.rajebackend.platform.infrastructure.persistence.jpa.repositories.PlatformRepository;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;

/**
 * Service implementation for handling platform commands such as sign-in and sign-up.
 */
@Service
public class PlatformCommandServiceImpl implements PlatformCommandService {

    private final PlatformRepository platformRepository;
    private final TokenService tokenService;
    private final HashingService hashingService;

    /**
     * Constructs a new PlatformCommandServiceImpl with the required dependencies.
     *
     * @param platformRepository the repository for platform persistence
     * @param tokenService the service responsible for generating authentication tokens
     * @param hashingService the service responsible for encoding and matching passwords
     */
    public PlatformCommandServiceImpl(PlatformRepository platformRepository, TokenService tokenService, HashingService hashingService) {
        this.platformRepository = platformRepository;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
    }

    /**
     * Handles the sign-in logic for a platform.
     * Validates the email and password, then generates an authentication token.
     *
     * @param command the sign-in command containing email and password
     * @return the authenticated Platform
     * @throws IllegalArgumentException if the email is not found or the password is incorrect
     */
    @Override
    @Transactional(readOnly = true)
    public Platform handle(SignInPlatformCommand command) {
        var platform = platformRepository.findByCredentialEmail(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Email not found: " + command.email()));

        // ✅ Verifica correctamente la contraseña hasheada
        if (!hashingService.matches(command.password(), platform.getCredential().password())) {
            throw new IllegalArgumentException("Invalid password");
        }

        var token = tokenService.generateToken(platform.getId());
        System.out.println("Token generado para plataforma: " + token);

        return platform;
    }

    /**
     * Handles the sign-up logic for a new platform.
     * Verifies if the email already exists, encodes the password, and persists the new platform.
     *
     * @param command the sign-up command containing platform registration details
     * @return the created Platform
     * @throws IllegalArgumentException if the email already exists
     */
    @Override
    public Platform handle(SignUpPlatformCommand command) {
        if (platformRepository.existsByCredentialEmail(command.email())) {
            throw new IllegalArgumentException("Email already exists: " + command.email());
        }

        String hashedPassword = hashingService.encode(command.password());

        var platform = new Platform(
                command.id(),
                command.nombre(),
                command.descripcion(),
                command.email(),
                hashedPassword,
                command.imagen()
        );

        return platformRepository.save(platform);
    }
}
