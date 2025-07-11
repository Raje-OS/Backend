package raje.com.rajebackend.library.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.commands.RemoveLibraryLocationCommand;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

@Service
public class RemoveLocationFromLibraryServiceImpl {

    private final LibraryRepository libraryRepository;

    public RemoveLocationFromLibraryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    public Library handle(RemoveLibraryLocationCommand command) {
        Library library = libraryRepository.findById(command.libraryId())
                .orElseThrow(() -> new RuntimeException("Library no encontrada"));

        library.removeUbicacionPorDireccion(command.direccion());

        return libraryRepository.save(library);
    }
}
