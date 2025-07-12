package raje.com.rajebackend.iam.domain.model.commands;

public record UpdateUserCommand(
        String id,
        String firstName,
        String lastName,
        String userName,
        String email,
        String images
) {}
