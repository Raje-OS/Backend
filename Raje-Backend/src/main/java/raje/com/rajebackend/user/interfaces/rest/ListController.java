package raje.com.rajebackend.user.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.user.application.internal.commandservices.ListCommandServiceImpl;
import raje.com.rajebackend.user.domain.model.commands.CreateListCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateListCommand;
import raje.com.rajebackend.user.domain.model.queries.GetListsByUserIdQuery;
import raje.com.rajebackend.user.domain.services.ListQueryService;
import raje.com.rajebackend.user.interfaces.rest.resources.CreateListResource;
import raje.com.rajebackend.user.interfaces.rest.resources.ListResource;
import raje.com.rajebackend.user.interfaces.rest.transform.CreateListCommandFromResourceAssembler;
import raje.com.rajebackend.user.interfaces.rest.transform.ListResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing user content lists.
 */
@RestController
@RequestMapping(value = "/api/v1/lists", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Lists", description = "Operations related to user content lists")
public class ListController {

    private final ListQueryService queryService;
    private final ListCommandServiceImpl commandService;

    /**
     * Constructs a new controller for handling list operations.
     *
     * @param queryService   the service for list queries
     * @param commandService the service for list commands
     */
    public ListController(ListQueryService queryService, ListCommandServiceImpl commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    /**
     * Retrieves all lists created by a specific user.
     *
     * @param userId the ID of the user
     * @return a list of {@link ListResource}, or 404 if none found
     */
    @GetMapping
    @Operation(summary = "Get lists by user ID", description = "Retrieve all lists for a specific user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Lists found"),
            @ApiResponse(responseCode = "404", description = "No lists found")
    })
    public ResponseEntity<List<ListResource>> getListsByUser(@RequestParam String userId) {
        var lists = queryService.handle(new GetListsByUserIdQuery(userId));
        if (lists.isEmpty()) return ResponseEntity.notFound().build();

        var resources = lists.stream()
                .map(ListResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    /**
     * Registers a new content list.
     *
     * @param resource the request body containing the list details
     * @return the created {@link ListResource}
     */
    @PostMapping
    @Operation(summary = "Create a list", description = "Register a new content list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid list data")
    })
    public ResponseEntity<ListResource> createList(@Valid @RequestBody CreateListResource resource) {
        var command = CreateListCommandFromResourceAssembler.toCommandFromResource(resource);
        var created = commandService.handle(command);
        return ResponseEntity.ok(ListResourceFromEntityAssembler.toResourceFromEntity(created));
    }

    /**
     * Updates an existing list by its ID.
     *
     * @param id      the ID of the list to update
     * @param command the request body with updated list data
     * @return the updated {@link ListResource}
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a list", description = "Update an existing list by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List updated successfully"),
            @ApiResponse(responseCode = "404", description = "List not found")
    })
    public ResponseEntity<ListResource> updateList(@PathVariable String id, @Valid @RequestBody UpdateListCommand command) {
        var updated = commandService.handle(id, command);
        return ResponseEntity.ok(ListResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    /**
     * Deletes a list by its ID.
     *
     * @param id the ID of the list to delete
     * @return an empty response with status 200 if successful
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete list by ID", description = "Deletes a list given its ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "List deleted successfully"),
            @ApiResponse(responseCode = "404", description = "List not found")
    })
    public ResponseEntity<Void> deleteList(@PathVariable String id) {
        commandService.handleDelete(id);
        return ResponseEntity.ok().build();
    }
}
