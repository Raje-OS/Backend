package raje.com.rajebackend.library.interfaces.rest.resources;

import java.util.List;

public record LibraryResource(
        String id,
        String nombre,
        String descripcion,
        String imagen,
        String email,
        String password,
        List<LocationResource> ubicaciones
) {
    public record LocationResource(double lat, double lng, String direccion) {}
}
