package raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.person.domain.model.aggregates.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, String> {
}
