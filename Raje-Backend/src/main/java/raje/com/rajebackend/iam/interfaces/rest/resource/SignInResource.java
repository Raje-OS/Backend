package raje.com.rajebackend.iam.interfaces.rest.resource;

public record SignInResource(
        String userName,
        String password
) {}