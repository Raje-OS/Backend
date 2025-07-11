package raje.com.rajebackend.person.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.domain.model.commands.CreateDirectorCommand;
import raje.com.rajebackend.person.domain.model.queries.GetDirectorByIdQuery;
import raje.com.rajebackend.person.domain.services.DirectorCommandService;
import raje.com.rajebackend.person.domain.services.DirectorQueryService;
import raje.com.rajebackend.person.interfaces.rest.resources.DirectorResource;
import raje.com.rajebackend.person.interfaces.rest.resources.CreateDirectorResource;
import raje.com.rajebackend.person.interfaces.rest.transform.DirectorResourceFromEntityAssembler;
import raje.com.rajebackend.person.interfaces.rest.transform.CreateDirectorCommandFromResourceAssembler;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/directors", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Directors", description = "Operations related to directors")
public class DirectorsController {

    private final DirectorCommandService commandService;
    private final DirectorQueryService queryService;

    public DirectorsController(DirectorCommandService commandService, DirectorQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a new director", description = "Creates a new director with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Director created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<DirectorResource> createDirector(@Valid @RequestBody CreateDirectorResource resource) {
        CreateDirectorCommand command = CreateDirectorCommandFromResourceAssembler.toCommandFromResource(resource);
        Director created = commandService.handle(command);

        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        var directorResponse = DirectorResourceFromEntityAssembler.toResourceFromEntity(created);
        return new ResponseEntity<>(directorResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{id}") // Cambié "directorId" por "id"
    @Operation(summary = "Get director by ID", description = "Retrieves a director using their ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Director found"),
            @ApiResponse(responseCode = "404", description = "Director not found")
    })
    public ResponseEntity<DirectorResource> getDirectorById(@PathVariable String id) { // Cambié "directorId" por "id"
        var result = queryService.handle(new GetDirectorByIdQuery(id));

        if (result.isEmpty()) return ResponseEntity.notFound().build();

        var resource = DirectorResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }
}
