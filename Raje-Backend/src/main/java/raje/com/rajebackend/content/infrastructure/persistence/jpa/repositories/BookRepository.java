package raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raje.com.rajebackend.content.domain.model.aggregates.Book;

public interface BookRepository extends JpaRepository<Book, String> {
}
