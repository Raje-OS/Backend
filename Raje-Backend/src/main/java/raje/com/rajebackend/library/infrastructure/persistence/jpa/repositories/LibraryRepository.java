package raje.com.rajebackend.library.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.library.domain.model.aggregates.Library;

import java.util.Optional;

@Repository
public interface LibraryRepository extends JpaRepository<Library, String> {
    Optional<Library> findByCredential_EmailIgnoreCase(String email);
}
