package raje.com.rajebackend.person.domain.model.commands;

import java.time.LocalDate;

public record CreateDirectorCommand(
        String id,
        String nombre,
        String descripcion,
        LocalDate fechaNacimiento,
        String ciudad_origen,
        String imagen
) {
}
