package raje.com.rajebackend.iam.interfaces.rest.resource;

import java.util.List;

/**
 * Resource class representing the user registration (sign-up) data.
 */
public record SignUpResource(
        String id,
        String firstName,
        String lastName,
        String userName,
        String email,
        String password,
        String images,
        List<String> platforms
) {}