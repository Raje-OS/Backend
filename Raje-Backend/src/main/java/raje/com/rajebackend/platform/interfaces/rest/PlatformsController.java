package raje.com.rajebackend.platform.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;
import raje.com.rajebackend.platform.domain.model.commands.SignInPlatformCommand;
import raje.com.rajebackend.platform.domain.model.queries.GetAllPlatformsQuery;
import raje.com.rajebackend.platform.domain.model.queries.GetPlatformByIdQuery;
import raje.com.rajebackend.platform.domain.model.queries.GetPlatformsByIdsQuery;
import raje.com.rajebackend.platform.domain.services.PlatformCommandService;
import raje.com.rajebackend.platform.domain.services.PlatformQueryService;
import raje.com.rajebackend.platform.interfaces.rest.resources.PlatformResource;
import raje.com.rajebackend.platform.interfaces.rest.resources.SignInPlatformResource;
import raje.com.rajebackend.platform.interfaces.rest.resources.SignUpPlatformResource;
import raje.com.rajebackend.platform.interfaces.rest.transform.PlatformResourceFromEntityAssembler;
import raje.com.rajebackend.platform.interfaces.rest.transform.SignUpPlatformCommandFromResourceAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/platforms", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Platforms", description = "Operations related to platforms")
public class PlatformsController {

    private final PlatformQueryService queryService;
    private final PlatformCommandService platformCommandService;
    private final TokenService tokenService;

    public PlatformsController(PlatformQueryService queryService,
                               PlatformCommandService platformCommandService,
                               TokenService tokenService) {
        this.queryService = queryService;
        this.platformCommandService = platformCommandService;
        this.tokenService = tokenService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get platform by ID", description = "Retrieve a platform by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platform found"),
            @ApiResponse(responseCode = "404", description = "Platform not found")
    })
    public ResponseEntity<PlatformResource> getPlatformById(@PathVariable String id) {
        var result = queryService.handle(new GetPlatformByIdQuery(id));

        if (result.isEmpty()) return ResponseEntity.notFound().build();

        var resource = PlatformResourceFromEntityAssembler.toResourceFromEntity(result.get());
        return ResponseEntity.ok(resource);
    }

    @GetMapping
    @Operation(summary = "Get all platforms", description = "Retrieve all platforms")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platforms found"),
            @ApiResponse(responseCode = "404", description = "Platforms not found")
    })
    public ResponseEntity<List<PlatformResource>> getAllPlatforms() {
        var platforms = queryService.handle(new GetAllPlatformsQuery());

        if (platforms == null || platforms.isEmpty()) return ResponseEntity.notFound().build();

        var platformResources = platforms.stream()
                .map(PlatformResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(platformResources);
    }

    @GetMapping("/by-ids")
    @Operation(summary = "Get platforms by multiple IDs", description = "Retrieve platforms given multiple IDs")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Platforms found"),
            @ApiResponse(responseCode = "404", description = "Platforms not found")
    })
    public ResponseEntity<List<PlatformResource>> getPlatformsByIds(@RequestParam List<String> ids) {
        var platforms = queryService.handle(new GetPlatformsByIdsQuery(ids));

        if (platforms == null || platforms.isEmpty()) return ResponseEntity.notFound().build();

        var platformResources = platforms.stream()
                .map(PlatformResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(platformResources);
    }

    @PostMapping("/sign-in")
    @Operation(summary = "Sign in as a platform", description = "Authenticate a platform and return a token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Authenticated successfully"),
            @ApiResponse(responseCode = "401", description = "Invalid credentials")
    })
    public ResponseEntity<SignInPlatformResource> signInPlatform(@RequestBody SignInPlatformCommand command) {
        var platform = platformCommandService.handle(command);
        var token = tokenService.generateToken(platform.getId());

        var resource = new SignInPlatformResource(platform.getId(), platform.getNombre(), token);
        return ResponseEntity.ok(resource);
    }


    @PostMapping("/sign-up")
    @Operation(summary = "Sign up as a platform", description = "Register a new platform")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Platform created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<PlatformResource> signUpPlatform(@RequestBody SignUpPlatformResource resource) {
        var command = SignUpPlatformCommandFromResourceAssembler.toCommandFromResource(resource);
        var platform = platformCommandService.handle(command);
        var response = PlatformResourceFromEntityAssembler.toResourceFromEntity(platform);
        return ResponseEntity.status(201).body(response);
    }
}
