package raje.com.rajebackend.content.domain.model.queries;

/**
 * Query object used to retrieve a single Movie entity by its ID.
 * This follows the CQRS (Command Query Responsibility Segregation) pattern.
 *
 * @param id the unique identifier of the movie to be retrieved
 */
public record GetMovieByIdQuery(String id) {}
