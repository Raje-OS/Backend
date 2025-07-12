package raje.com.rajebackend.library.interfaces.rest.resources;

import java.util.List;

public record SignUpLibraryResource(
        String id,
        String nombre,
        String descripcion,
        String imagen,
        String password,
        String email,
        List<LibraryResource.LocationResource> ubicaciones
) {}
