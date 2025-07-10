package raje.com.rajebackend.person.domain.model.commands;

import java.time.LocalDate;

public record CreateAuthorCommand(
        String nombre,
        String descripcion,
        LocalDate fechaNacimiento,
        String ciudad,
        String pais,
        String imagen
) {
}
