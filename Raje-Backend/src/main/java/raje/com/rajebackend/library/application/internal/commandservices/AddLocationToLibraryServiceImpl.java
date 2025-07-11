package raje.com.rajebackend.library.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.commands.AddLibraryLocationCommand;
import raje.com.rajebackend.library.domain.model.valueobjects.Location;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

@Service
public class AddLocationToLibraryServiceImpl {

    private final LibraryRepository libraryRepository;

    public AddLocationToLibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library handle(AddLibraryLocationCommand command) {
        Library library = libraryRepository.findById(command.libraryId())
                .orElseThrow(() -> new RuntimeException("Library no encontrada"));

        Location nueva = new Location(command.lat(), command.lng(), command.direccion());
        library.addUbicacion(nueva);

        return libraryRepository.save(library);
    }
}
