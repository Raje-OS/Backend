package raje.com.rajebackend.person.interfaces.rest.resources;

import java.time.LocalDate;

public record AuthorResource(
        String idAuthor,
        String nombre,
        String descripcion,
        LocalDate fechaNacimiento,
        String ciudad,
        String pais,
        String imagen
) {
}
