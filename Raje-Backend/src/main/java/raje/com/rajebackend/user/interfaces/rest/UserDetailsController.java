package raje.com.rajebackend.user.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.user.application.internal.commandservices.UserDetailsCommandServiceImpl;
import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;
import raje.com.rajebackend.user.domain.model.commands.CreateUserDetailsCommand;
import raje.com.rajebackend.user.domain.services.UserDetailsQueryService;
import raje.com.rajebackend.user.interfaces.rest.resources.CreateUserDetailsResource;
import raje.com.rajebackend.user.interfaces.rest.resources.UserDetailsResource;
import raje.com.rajebackend.user.interfaces.rest.transform.CreateUserDetailsCommandFromResourceAssembler;
import raje.com.rajebackend.user.interfaces.rest.transform.UserDetailsResourceFromEntityAssembler;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/userdetails", produces = APPLICATION_JSON_VALUE)
@Tag(name = "UserDetails", description = "Operations related to user details")
public class UserDetailsController {

    private final UserDetailsQueryService queryService;
    private final UserDetailsCommandServiceImpl commandService;

    public UserDetailsController(UserDetailsQueryService queryService,
                                 UserDetailsCommandServiceImpl commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    @GetMapping("/user/{userId}")
    @Operation(summary = "Get user details by user ID", description = "Retrieve user details for a given user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserDetails found"),
            @ApiResponse(responseCode = "404", description = "UserDetails not found")
    })
    public ResponseEntity<UserDetailsResource> getByUserId(@PathVariable String userId) {
        Optional<UserDetails> result = queryService.findByUserId(userId);
        return result.map(userDetails ->
                        ResponseEntity.ok(UserDetailsResourceFromEntityAssembler.toResourceFromEntity(userDetails)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create user details", description = "Register a new user details entry")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserDetails created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid request")
    })
    public ResponseEntity<UserDetailsResource> create(@Valid @RequestBody CreateUserDetailsResource resource) {
        CreateUserDetailsCommand command = CreateUserDetailsCommandFromResourceAssembler.toCommandFromResource(resource);
        UserDetails created = commandService.handle(command);
        return ResponseEntity.ok(UserDetailsResourceFromEntityAssembler.toResourceFromEntity(created));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update user details", description = "Update favorites and viewed list")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "UserDetails updated successfully"),
            @ApiResponse(responseCode = "400", description = "ID mismatch or invalid data"),
            @ApiResponse(responseCode = "404", description = "UserDetails not found")
    })
    public ResponseEntity<UserDetailsResource> update(@PathVariable String id,
                                                      @Valid @RequestBody UserDetails resource) {
        if (!id.equals(resource.getId())) return ResponseEntity.badRequest().build();

        UserDetails updated = commandService.handle(id, resource);
        return ResponseEntity.ok(UserDetailsResourceFromEntityAssembler.toResourceFromEntity(updated));
    }
}
