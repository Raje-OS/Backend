package raje.com.rajebackend.library.application.internal.queryservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.iam.application.internal.outboundservices.hashing.HashingService;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.commands.SignInLibraryCommand;
import raje.com.rajebackend.library.domain.model.commands.SignUpLibraryCommand;
import raje.com.rajebackend.library.domain.services.LibraryCommandService;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

/**
 * Service implementation that handles commands related to library sign-up and sign-in operations.
 */
@Service
public class LibraryCommandServiceImpl implements LibraryCommandService {

    private final LibraryRepository libraryRepository;
    private final TokenService tokenService;
    private final HashingService hashingService;

    public LibraryCommandServiceImpl(LibraryRepository libraryRepository,
                                     TokenService tokenService,
                                     HashingService hashingService) {
        this.libraryRepository = libraryRepository;
        this.tokenService = tokenService;
        this.hashingService = hashingService;
    }

    /**
     * Handles the sign-up command for a new library.
     *
     * @param command the sign-up command with library credentials
     * @return the newly registered library
     * @throws IllegalArgumentException if the email already exists
     */
    @Override
    @Transactional
    public Library handle(SignUpLibraryCommand command) {
        if (libraryRepository.existsByCredentialEmail(command.email())) {
            throw new IllegalArgumentException("Email already exists: " + command.email());
        }

        var hashedPassword = hashingService.encode(command.password());

        var library = new Library(
                command.id(),
                command.nombre(),
                command.descripcion(),
                command.imagen(),
                command.email(),
                hashedPassword
        );

        return libraryRepository.save(library);
    }

    /**
     * Handles the sign-in command for a library.
     *
     * @param command the sign-in credentials
     * @return the authenticated library
     * @throws IllegalArgumentException if email is not found or password is invalid
     */
    @Override
    @Transactional(readOnly = true)
    public Library handle(SignInLibraryCommand command) {
        var library = libraryRepository.findByCredential_EmailIgnoreCase(command.email())
                .orElseThrow(() -> new IllegalArgumentException("Email not found: " + command.email()));

        if (!hashingService.matches(command.password(), library.getCredential().password())) {
            throw new IllegalArgumentException("Invalid password");
        }

        String token = tokenService.generateToken(library.getId().toString());
        System.out.println("Generated token for library: " + token);

        return library;
    }

    /**
     * Generates a JWT token for the given library.
     *
     * @param library the library entity
     * @return the generated token as String
     */
    @Override
    public String generateTokenForLibrary(Library library) {
        return tokenService.generateToken(library.getId().toString());
    }
}
