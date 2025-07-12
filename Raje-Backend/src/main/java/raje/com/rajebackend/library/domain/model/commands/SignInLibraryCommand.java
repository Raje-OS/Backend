package raje.com.rajebackend.library.domain.model.commands;

public record SignInLibraryCommand(
        String email,
        String password
) {}
