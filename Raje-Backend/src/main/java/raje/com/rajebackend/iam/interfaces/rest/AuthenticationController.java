package raje.com.rajebackend.iam.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.domain.services.UserCommandService;
import raje.com.rajebackend.iam.interfaces.rest.resource.AuthenticateUserResource;
import raje.com.rajebackend.iam.interfaces.rest.resource.AuthenticatedUserResource;
import raje.com.rajebackend.iam.interfaces.rest.resource.SignUpResource;
import raje.com.rajebackend.iam.interfaces.rest.transform.AuthenticatedUserResourceFromEntityAssembler;
import raje.com.rajebackend.iam.interfaces.rest.transform.SignInCommandFromResourceAssembler;
import raje.com.rajebackend.iam.interfaces.rest.transform.SignUpCommandFromResourceAssembler;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller that handles authentication-related operations such as sign-in and sign-up.
 */
@RestController
@RequestMapping(value = "/api/v1/authentication", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Authentication", description = "Operations related to user authentication")
public class AuthenticationController {

    private final UserCommandService userCommandService;
    private final TokenService tokenService;

    public AuthenticationController(UserCommandService userCommandService, TokenService tokenService) {
        this.userCommandService = userCommandService;
        this.tokenService = tokenService;
    }

    /**
     * Authenticates a user based on credentials and returns user data with a JWT token.
     *
     * @param resource the user credentials
     * @return authenticated user resource including a JWT token
     */
    @PostMapping("/sign-in")
    @Operation(summary = "Sign in", description = "Authenticate a user with username and password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User authenticated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid credentials or bad request")
    })
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody AuthenticateUserResource resource) {
        var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);
        var token = tokenService.generateToken(user.getUserName());
        var pair = ImmutablePair.of(user, token);
        var response = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(pair);
        return ResponseEntity.ok(response);
    }

    /**
     * Registers a new user and returns their data along with a JWT token.
     *
     * @param resource the user registration data
     * @return the newly registered user and token
     */
    @PostMapping("/sign-up")
    @Operation(summary = "Sign up", description = "Register a new user and return a JWT token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "User already exists or invalid input")
    })
    public ResponseEntity<AuthenticatedUserResource> signUp(@RequestBody SignUpResource resource) {
        var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        User user = userCommandService.handle(command);
        String token = tokenService.generateToken(user.getUserName());

        var pair = ImmutablePair.of(user, token);
        var response = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(pair);
        return ResponseEntity.ok(response);
    }
}
