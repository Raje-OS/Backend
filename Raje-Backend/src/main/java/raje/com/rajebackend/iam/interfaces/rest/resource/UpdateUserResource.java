package raje.com.rajebackend.iam.interfaces.rest.resource;

public record UpdateUserResource(
        String id,
        String firstName,
        String lastName,
        String userName,
        String email,
        String images
) {}
