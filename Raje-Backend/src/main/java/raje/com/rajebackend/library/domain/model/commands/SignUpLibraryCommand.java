package raje.com.rajebackend.library.domain.model.commands;

public record SignUpLibraryCommand(
        String id,
        String nombre,
        String descripcion,
        String imagen,
        String email,
        String password
) {}
