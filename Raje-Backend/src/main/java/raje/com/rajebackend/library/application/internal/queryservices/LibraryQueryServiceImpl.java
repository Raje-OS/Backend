package raje.com.rajebackend.library.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByEmailQuery;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByIdQuery;
import raje.com.rajebackend.library.domain.services.LibraryQueryService;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

import java.util.Optional;

/**
 * Service implementation for handling library queries.
 */
@Service
public class LibraryQueryServiceImpl implements LibraryQueryService {

    private final LibraryRepository libraryRepository;

    public LibraryQueryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    /**
     * Retrieves a library by its ID.
     *
     * @param query the query containing the library ID
     * @return an Optional with the Library, or empty if not found
     */
    @Override
    public Optional<Library> handle(GetLibraryByIdQuery query) {
        return libraryRepository.findById(query.id());
    }

    /**
     * Retrieves a library by its email, ignoring case and trimming spaces.
     *
     * @param query the query containing the library email
     * @return an Optional with the Library, or empty if not found
     */
    @Override
    public Optional<Library> handle(GetLibraryByEmailQuery query) {
        if (query.email() == null || query.email().isBlank()) return Optional.empty();
        return libraryRepository.findByCredential_EmailIgnoreCase(query.email().trim());
    }
}
