package raje.com.rajebackend.library.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.commands.AddLibraryLocationCommand;
import raje.com.rajebackend.library.domain.model.valueobjects.Location;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

/**
 * Application service responsible for adding a new location to an existing library.
 */
@Service
public class AddLocationToLibraryServiceImpl {

    private final LibraryRepository libraryRepository;

    public AddLocationToLibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Handles the addition of a new location to a library.
     *
     * @param command the command containing the library ID and location details
     * @return the updated Library aggregate with the new location added
     * @throws RuntimeException if the library is not found
     */
    @Transactional
    public Library handle(AddLibraryLocationCommand command) {
        var library = libraryRepository.findById(command.libraryId())
                .orElseThrow(() -> new RuntimeException("Library not found with ID: " + command.libraryId()));

        var newLocation = new Location(command.lat(), command.lng(), command.direccion());
        library.addUbicacion(newLocation);

        return libraryRepository.save(library);
    }
}
