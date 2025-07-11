package raje.com.rajebackend.library.domain.services;

import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByEmailQuery;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByIdQuery;

import java.util.Optional;

public interface LibraryQueryService {
    Optional<Library> handle(GetLibraryByIdQuery query);
    Optional<Library> handle(GetLibraryByEmailQuery query);
}
