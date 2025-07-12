package raje.com.rajebackend.iam.interfaces.rest.resource;

import java.util.List;

/**
 * Resource class representing the exposed user data.
 */
public record UserResource(
        String id,
        String firstName,
        String lastName,
        String userName,
        String email,
        String images,
        List<String> platforms
) {}
