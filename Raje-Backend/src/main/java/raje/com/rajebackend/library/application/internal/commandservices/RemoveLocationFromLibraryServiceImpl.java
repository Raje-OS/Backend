package raje.com.rajebackend.library.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.commands.RemoveLibraryLocationCommand;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

/**
 * Service responsible for handling the removal of a location from a library.
 */
@Service
public class RemoveLocationFromLibraryServiceImpl {

    private final LibraryRepository libraryRepository;

    public RemoveLocationFromLibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Handles the command to remove a location from a library.
     *
     * @param command the command containing the library ID and address to remove
     * @return the updated Library entity
     * @throws IllegalArgumentException if the library is not found
     */
    @Transactional
    public Library handle(RemoveLibraryLocationCommand command) {
        Library library = libraryRepository.findById(command.libraryId())
                .orElseThrow(() -> new IllegalArgumentException("Library not found with ID: " + command.libraryId()));

        library.removeUbicacionPorDireccion(command.direccion());

        return libraryRepository.save(library);
    }
}
