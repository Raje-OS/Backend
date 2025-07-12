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

@Service
public class PlatformCommandServiceImpl implements PlatformCommandService {

    private final PlatformRepository platformRepository;
    private final TokenService tokenService;
    private final HashingService hashingService;

    public PlatformCommandServiceImpl(PlatformRepository platformRepository, TokenService tokenService, HashingService hashingService) {
        this.platformRepository = platformRepository;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
    }

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
