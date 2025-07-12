package raje.com.rajebackend.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.iam.domain.model.queries.GetAllUsersQuery;
import raje.com.rajebackend.iam.domain.model.queries.GetUserByIdQuery;
import raje.com.rajebackend.iam.domain.services.UserCommandService;
import raje.com.rajebackend.iam.domain.services.UserQueryService;
import raje.com.rajebackend.iam.interfaces.rest.resource.UpdateUserResource;
import raje.com.rajebackend.iam.interfaces.rest.resource.UserResource;
import raje.com.rajebackend.iam.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import raje.com.rajebackend.iam.interfaces.rest.transform.UserResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST Controller for handling user management operations.
 */
@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Operations related to user management")
public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    /**
     * Retrieves a list of all registered users.
     *
     * @return a list of user resources
     */
    @GetMapping
    @Operation(summary = "Get all users", description = "Retrieve a list of all users in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users retrieved successfully")
    })
    public ResponseEntity<List<UserResource>> getAllUsers() {
        var users = userQueryService.handle(new GetAllUsersQuery());
        var resources = users.stream()
                .map(UserResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    /**
     * Retrieves a specific user by ID.
     *
     * @param id the ID of the user
     * @return the user resource if found, otherwise 404
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID", description = "Retrieve details of a specific user by their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> getUserById(@PathVariable String id) {
        var user = userQueryService.handle(new GetUserByIdQuery(id));
        return user.map(value ->
                ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(value))
        ).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Updates the details of a specific user.
     *
     * @param id       the ID of the user to update
     * @param resource the updated user data
     * @return the updated user resource
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update user", description = "Update an existing user's profile by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request or mismatched ID"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    public ResponseEntity<UserResource> updateUser(@PathVariable String id, @RequestBody UpdateUserResource resource) {
        if (!id.equals(resource.id())) {
            return ResponseEntity.badRequest().build();
        }

        var command = UpdateUserCommandFromResourceAssembler.toCommandFromResource(resource);
        var updatedUser = userCommandService.handle(id, command);
        var updatedResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser);
        return ResponseEntity.ok(updatedResource);
    }
}
