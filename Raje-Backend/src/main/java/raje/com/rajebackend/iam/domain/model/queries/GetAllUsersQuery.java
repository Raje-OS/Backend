package raje.com.rajebackend.iam.domain.model.queries;

/**
 * Query object used to request all users in the system.
 * This class follows the CQRS (Command Query Responsibility Segregation) pattern.
 * It does not require any parameters.
 */
public record GetAllUsersQuery() {}
