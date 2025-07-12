package raje.com.rajebackend.platform.domain.model.commands;


public record SignUpPlatformCommand(
        String id,
        String nombre,
        String descripcion,
        String imagen,
        String email,
        String password
) {}
