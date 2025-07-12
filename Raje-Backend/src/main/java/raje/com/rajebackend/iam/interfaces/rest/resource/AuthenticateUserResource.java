package raje.com.rajebackend.iam.interfaces.rest.resource;

public record AuthenticateUserResource(
        String identifier,
        String password
) {}