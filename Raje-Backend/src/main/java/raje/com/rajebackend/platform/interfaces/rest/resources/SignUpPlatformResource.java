package raje.com.rajebackend.platform.interfaces.rest.resources;


public record SignUpPlatformResource(
        String id,
        String nombre,
        String descripcion,
        String imagen,    // Imagen ahora está en su lugar correcto
        String email,
        String password
) {}
