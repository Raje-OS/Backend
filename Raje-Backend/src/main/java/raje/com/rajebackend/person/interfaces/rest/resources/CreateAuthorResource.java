package raje.com.rajebackend.person.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateAuthorResource(

        @NotBlank(message = "El id es obligatorio") // Agregar validación si es necesario
        String id, // Aquí agregamos el campo id como String primero

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 200, message = "El nombre no puede exceder los 200 caracteres")
        String nombre,

        @NotBlank(message = "La descripción no puede estar vacía")
        @Size(max = 4000, message = "La descripción no puede exceder los 4000 caracteres")
        String descripcion,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe estar en el pasado")
        LocalDate fechaNacimiento,

        @NotBlank(message = "La ciudad es obligatoria")
        String ciudad_origen,

        @NotBlank(message = "La URL de la imagen es obligatoria")
        String imagen
) {
}
