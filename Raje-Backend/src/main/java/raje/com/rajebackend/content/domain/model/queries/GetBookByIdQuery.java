package raje.com.rajebackend.content.domain.model.queries;

/**
 * Query object used to retrieve a single Book entity by its ID.
 * This follows the CQRS (Command Query Responsibility Segregation) pattern.
 *
 * @param id the unique identifier of the book to be retrieved
 */
public record GetBookByIdQuery(String id) {}
