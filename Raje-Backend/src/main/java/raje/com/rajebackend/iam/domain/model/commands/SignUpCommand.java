package raje.com.rajebackend.iam.domain.model.commands;

import java.util.List;

public record SignUpCommand(
        String id,
        String firstName,
        String lastName,
        String userName,
        String email,
        String password,
        List<String> platforms,
        String images
) {}
