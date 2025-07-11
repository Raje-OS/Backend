package raje.com.rajebackend.content.domain.services;

import raje.com.rajebackend.content.domain.model.aggregates.Book;
import raje.com.rajebackend.content.domain.model.queries.GetAllBooksQuery;
import raje.com.rajebackend.content.domain.model.queries.GetBookByIdQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllBooksOrderedByRatingQuery;

import java.util.List;
import java.util.Optional;

public interface BookQueryService {
    List<Book> handle(GetAllBooksQuery query);
    Optional<Book> handle(GetBookByIdQuery query);
    List<Book> handle(GetAllBooksOrderedByRatingQuery query);
}
