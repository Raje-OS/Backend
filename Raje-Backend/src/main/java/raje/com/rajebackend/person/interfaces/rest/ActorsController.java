package raje.com.rajebackend.person.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.domain.model.commands.CreateActorCommand;
import raje.com.rajebackend.person.domain.model.queries.GetActorByIdQuery;
import raje.com.rajebackend.person.domain.model.queries.GetActorsByIdsQuery;
import raje.com.rajebackend.person.domain.services.ActorCommandService;
import raje.com.rajebackend.person.domain.services.ActorQueryService;
import raje.com.rajebackend.person.interfaces.rest.resources.ActorResource;
import raje.com.rajebackend.person.interfaces.rest.resources.CreateActorResource;
import raje.com.rajebackend.person.interfaces.rest.transform.ActorResourceFromEntityAssembler;
import raje.com.rajebackend.person.interfaces.rest.transform.CreateActorCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/actors", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Actors", description = "Operations related to actors")
public class ActorsController {

    private final ActorCommandService commandService;
    private final ActorQueryService queryService;

    public ActorsController(ActorCommandService commandService, ActorQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @PostMapping
    @Operation(summary = "Create a new actor", description = "Creates a new actor with the provided details.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Actor created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    public ResponseEntity<ActorResource> createActor(@Valid @RequestBody CreateActorResource resource) {
        CreateActorCommand command = CreateActorCommandFromResourceAssembler.toCommandFromResource(resource);
        Actor created = commandService.handle(command);

        if (created == null) {
            return ResponseEntity.badRequest().build();
        }

        var actorResponse = ActorResourceFromEntityAssembler.toResourceFromEntity(created);
        return new ResponseEntity<>(actorResponse, HttpStatus.CREATED);
    }

    @GetMapping("/{actorId}")
    @Operation(summary = "Get actor by ID", description = "Retrieves an actor using its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actor found"),
            @ApiResponse(responseCode = "404", description = "Actor not found")
    })
    public ResponseEntity<ActorResource> getActorById(@PathVariable String actorId) {
        var result = queryService.handle(new GetActorByIdQuery(actorId));

        if (result.isEmpty()) return ResponseEntity.notFound().build();

        var resource = ActorResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    @Operation(summary = "Get actors by multiple IDs", description = "Retrieves a list of actors given multiple IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Actors found"),
            @ApiResponse(responseCode = "404", description = "Actors not found")
    })
    public ResponseEntity<List<ActorResource>> getActorsByIds(@RequestParam List<String> id) {
        var actors = queryService.handle(new GetActorsByIdsQuery(id));

        if (actors == null || actors.isEmpty()) return ResponseEntity.notFound().build();

        var actorResources = actors.stream()
                .map(ActorResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(actorResources);
    }
}
