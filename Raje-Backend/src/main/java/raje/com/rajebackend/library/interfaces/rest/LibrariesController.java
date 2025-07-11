package raje.com.rajebackend.library.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.library.application.internal.commandservices.AddLocationToLibraryServiceImpl;
import raje.com.rajebackend.library.application.internal.commandservices.RemoveLocationFromLibraryServiceImpl;
import raje.com.rajebackend.library.domain.model.commands.AddLibraryLocationCommand;
import raje.com.rajebackend.library.domain.model.commands.RemoveLibraryLocationCommand;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByEmailQuery;
import raje.com.rajebackend.library.domain.model.queries.GetLibraryByIdQuery;
import raje.com.rajebackend.library.domain.services.LibraryQueryService;
import raje.com.rajebackend.library.interfaces.rest.resources.LibraryResource;
import raje.com.rajebackend.library.interfaces.rest.transform.LibraryResourceFromEntityAssembler;

import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/libraries", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Libraries", description = "Operations related to libraries")
public class LibrariesController {

    private final LibraryQueryService queryService;
    private final AddLocationToLibraryServiceImpl addService;
    private final RemoveLocationFromLibraryServiceImpl removeService;

    public LibrariesController(LibraryQueryService queryService,
                               AddLocationToLibraryServiceImpl addService,
                               RemoveLocationFromLibraryServiceImpl removeService) {
        this.queryService = queryService;
        this.addService = addService;
        this.removeService = removeService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get library by ID", description = "Retrieve a library by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library found"),
            @ApiResponse(responseCode = "404", description = "Library not found")
    })
    public ResponseEntity<LibraryResource> getLibraryById(@PathVariable String id) {
        var result = queryService.handle(new GetLibraryByIdQuery(id));
        return result.map(library ->
                        ResponseEntity.ok(LibraryResourceFromEntityAssembler.toResourceFromEntity(library)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    @Operation(summary = "Get library by email", description = "Retrieve a library by its email")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Library found"),
            @ApiResponse(responseCode = "404", description = "Library not found")
    })
    public ResponseEntity<LibraryResource> getLibraryByEmail(@RequestParam String email) {
        var result = queryService.handle(new GetLibraryByEmailQuery(email));
        return result.map(library ->
                        ResponseEntity.ok(LibraryResourceFromEntityAssembler.toResourceFromEntity(library)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/add-location")
    @Operation(summary = "Add location to library", description = "Add a new location to a library")
    public ResponseEntity<LibraryResource> addLocation(@PathVariable String id,
                                                       @RequestBody LibraryResource.LocationResource body) {
        var command = new AddLibraryLocationCommand(id, body.lat(), body.lng(), body.direccion());
        var updated = addService.handle(command);
        return ResponseEntity.ok(LibraryResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    @PutMapping("/{id}/remove-location")
    @Operation(summary = "Remove location from library", description = "Remove a location from a library by direccion")
    public ResponseEntity<LibraryResource> removeLocation(@PathVariable String id,
                                                          @RequestBody Map<String, String> payload) {
        var direccion = payload.get("direccion");
        var command = new RemoveLibraryLocationCommand(id, direccion);
        var updated = removeService.handle(command);
        return ResponseEntity.ok(LibraryResourceFromEntityAssembler.toResourceFromEntity(updated));
    }
}
