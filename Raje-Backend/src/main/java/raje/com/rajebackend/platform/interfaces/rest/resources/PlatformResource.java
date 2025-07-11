package raje.com.rajebackend.platform.interfaces.rest.resources;

public record PlatformResource(
        String id,
        String nombre,
        String descripcion,
        String imagen,
        String email,
        String password
) {}
