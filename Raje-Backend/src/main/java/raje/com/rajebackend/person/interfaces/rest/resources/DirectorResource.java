package raje.com.rajebackend.person.interfaces.rest.resources;

import java.time.LocalDate;

public record DirectorResource(
        String id,
        String nombre,
        String descripcion,
        LocalDate fechaNacimiento,
        String ciudad_origen,
        String imagen
) {
}
