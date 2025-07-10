package raje.com.rajebackend.person.interfaces.rest.resources;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record CreateActorResource(

        @NotBlank(message = "El nombre no puede estar vacío")
        @Size(max = 80, message = "El nombre no puede exceder los 80 caracteres")
        String nombre,

        @NotBlank(message = "La descripción no puede estar vacía")
        @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres")
        String descripcion,

        @NotNull(message = "La fecha de nacimiento es obligatoria")
        @Past(message = "La fecha de nacimiento debe estar en el pasado")
        LocalDate fechaNacimiento,

        @NotBlank(message = "La ciudad es obligatoria")
        String ciudad,

        @NotBlank(message = "El país es obligatorio")
        String pais,

        @NotBlank(message = "La URL de la imagen es obligatoria")
        String imagen

) {}
