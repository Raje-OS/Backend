package raje.com.rajebackend.library.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByEmailQuery;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByIdQuery;
import raje.com.rajebackend.library.domain.services.LibraryQueryService;
import raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories.LibraryRepository;

import java.util.Optional;

@Service
public class LibraryQueryServiceImpl implements LibraryQueryService {

    private final LibraryRepository libraryRepository;

    public LibraryQueryServiceImpl(LibraryRepository libraryRepository) {
        this.libraryRepository = libraryRepository;
    }

    @Override
    public Optional<Library> handle(GetLibraryByIdQuery query) {
        return libraryRepository.findById(query.id());
    }

    @Override
    public Optional<Library> handle(GetLibraryByEmailQuery query) {
        return libraryRepository.findByCredential_EmailIgnoreCase(query.email().trim());
    }
}
