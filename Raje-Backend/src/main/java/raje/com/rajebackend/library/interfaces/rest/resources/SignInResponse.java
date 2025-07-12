package raje.com.rajebackend.library.interfaces.rest.resources;


public record SignInResponse(
        String token,
        String libraryId,
        String email
) {}