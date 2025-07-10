package raje.com.rajebackend.person.interfaces.rest.resources;

import java.time.LocalDate;

public record ActorResource(
        String idActor,
        String nombre,
        String descripcion,
        LocalDate fechaNacimiento,
        String ciudad,
        String pais,
        String imagen
) {}
