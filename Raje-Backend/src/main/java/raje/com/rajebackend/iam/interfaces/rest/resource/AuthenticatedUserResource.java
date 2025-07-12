package raje.com.rajebackend.iam.interfaces.rest.resource;

import java.util.List;

public record AuthenticatedUserResource(
        String id,
        String firstName,
        String lastName,
        String userName,
        String email,
        List<String> platforms,
        String images,
        String token
) {}