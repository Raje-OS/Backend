package raje.com.rajebackend.content.domain.model.queries;

/**
 * Query object used to retrieve a single Serie entity by its ID.
 * This follows the CQRS (Command Query Responsibility Segregation) pattern.
 *
 * @param id the unique identifier of the serie to be retrieved
 */
public record GetSerieByIdQuery(String id) {}
