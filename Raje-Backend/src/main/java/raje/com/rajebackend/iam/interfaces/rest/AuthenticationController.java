package raje.com.rajebackend.iam.interfaces.rest;

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

@RestController
@RequestMapping("/api/v1/authentication")
public class AuthenticationController {

    private final UserCommandService userCommandService;
    private final TokenService tokenService;

    public AuthenticationController(UserCommandService userCommandService, TokenService tokenService) {
        this.userCommandService = userCommandService;
        this.tokenService = tokenService;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<AuthenticatedUserResource> signIn(@RequestBody AuthenticateUserResource resource) {
        var command = SignInCommandFromResourceAssembler.toCommandFromResource(resource);
        var user = userCommandService.handle(command);
        var token = tokenService.generateToken(user.getUserName());
        var pair = ImmutablePair.of(user, token);
        var response = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(pair);
        return ResponseEntity.ok(response);
    }



    @PostMapping("/sign-up")
    public ResponseEntity<AuthenticatedUserResource> signUp(@RequestBody SignUpResource resource) {
        var command = SignUpCommandFromResourceAssembler.toCommandFromResource(resource);
        User user = userCommandService.handle(command);
        String token = tokenService.generateToken(user.getUserName());

        var pair = ImmutablePair.of(user, token);
        var response = AuthenticatedUserResourceFromEntityAssembler.toResourceFromEntity(pair);
        return ResponseEntity.ok(response);
    }
}
