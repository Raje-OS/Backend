package raje.com.rajebackend.content.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.content.domain.model.aggregates.Book;
import raje.com.rajebackend.content.domain.model.queries.GetAllBooksQuery;
import raje.com.rajebackend.content.domain.model.queries.GetBookByIdQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllBooksOrderedByRatingQuery;
import raje.com.rajebackend.content.domain.services.BookQueryService;
import raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories.BookRepository;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class BookQueryServiceImpl implements BookQueryService {

    // Repository used to access the Book data from the database
    private final BookRepository bookRepository;

    // Constructor injecting the BookRepository dependency
    public BookQueryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    /**
     * Handles the query to retrieve all books from the repository.
     * @param query the query object (not used in this case)
     * @return a list of all Book entities
     */
    @Override
    public List<Book> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }

    /**
     * Handles the query to retrieve a single book by its ID.
     * @param query contains the ID of the book to be retrieved
     * @return an Optional containing the Book if found, or empty if not
     */
    @Override
    public Optional<Book> handle(GetBookByIdQuery query) {
        return bookRepository.findById(query.id());
    }

    /**
     * Handles the query to retrieve all books ordered by rating in descending order.
     * @param query the query object (not used in this case)
     * @return a list of books sorted by their rating, highest first
     */
    @Override
    public List<Book> handle(GetAllBooksOrderedByRatingQuery query) {
        return bookRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Book::getCalificacion).reversed())
                .toList();
    }
}
