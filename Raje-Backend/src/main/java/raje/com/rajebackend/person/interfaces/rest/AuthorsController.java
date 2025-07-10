package raje.com.rajebackend.person.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.person.domain.model.aggregates.Author;
import raje.com.rajebackend.person.domain.model.commands.CreateAuthorCommand;
import raje.com.rajebackend.person.domain.model.queries.GetAuthorByIdQuery;
import raje.com.rajebackend.person.domain.services.AuthorCommandService;
import raje.com.rajebackend.person.domain.services.AuthorQueryService;
import raje.com.rajebackend.person.interfaces.rest.resources.AuthorResource;
import raje.com.rajebackend.person.interfaces.rest.resources.CreateAuthorResource;
import raje.com.rajebackend.person.interfaces.rest.transform.AuthorResourceFromEntityAssembler;
import raje.com.rajebackend.person.interfaces.rest.transform.CreateAuthorCommandFromResourceAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/authors", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Authors", description = "Operations related to authors")
public class AuthorsController {

    private final AuthorCommandService commandService;
    private final AuthorQueryService queryService;

    public AuthorsController(AuthorCommandService commandService, AuthorQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a new author", description = "Creates a new author with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Author created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<AuthorResource> createAuthor(@Valid @RequestBody CreateAuthorResource resource) {
        CreateAuthorCommand command = CreateAuthorCommandFromResourceAssembler.toCommandFromResource(resource);
        Author created = commandService.handle(command);

        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        var authorResponse = AuthorResourceFromEntityAssembler.toResourceFromEntity(created);
        return new ResponseEntity<>(authorResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{authorId}")
    @Operation(summary = "Get author by ID", description = "Retrieves an author using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Author found"),
            @ApiResponse(responseCode = "404", description = "Author not found")
    })
    public ResponseEntity<AuthorResource> getAuthorById(@PathVariable String authorId) {
        var result = queryService.handle(new GetAuthorByIdQuery(authorId));

        if (result.isEmpty()) return ResponseEntity.notFound().build();

        var resource = AuthorResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }
}
