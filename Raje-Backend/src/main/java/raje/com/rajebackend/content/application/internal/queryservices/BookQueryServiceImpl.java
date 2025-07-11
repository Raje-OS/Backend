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

    private final BookRepository bookRepository;

    public BookQueryServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> handle(GetAllBooksQuery query) {
        return bookRepository.findAll();
    }

    @Override
    public Optional<Book> handle(GetBookByIdQuery query) {
        return bookRepository.findById(query.id());
    }

    @Override
    public List<Book> handle(GetAllBooksOrderedByRatingQuery query) {
        return bookRepository.findAll().stream()
                .sorted(Comparator.comparingDouble(Book::getCalificacion).reversed())
                .toList();
    }
}
