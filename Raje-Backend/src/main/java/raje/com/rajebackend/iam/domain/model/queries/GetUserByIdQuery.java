package raje.com.rajebackend.iam.domain.model.queries;

/**
 * Query object used to retrieve a single User entity by its ID.
 * This class is part of the CQRS (Command Query Responsibility Segregation) pattern.
 *
 * @param id the unique identifier of the user to retrieve
 */
public record GetUserByIdQuery(String id) {}
