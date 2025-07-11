package raje.com.rajebackend.content.interfaces.rest.resources;

import java.time.LocalDate;
import java.util.List;

public record BookResource(
        String id,
        String titulo,
        String autorId,
        List<String> genero,
        int numPaginas,
        LocalDate fechaPublicacion,
        String idiomaOriginal,
        String paisOrigen,
        String editorial,
        String isbn,
        double calificacion,
        String sinopsis,
        String imagen,
        List<String> libreriasId
) {}
