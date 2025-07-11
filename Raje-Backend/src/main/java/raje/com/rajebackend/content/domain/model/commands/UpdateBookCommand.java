// UpdateBookCommand.java
package raje.com.rajebackend.content.domain.model.commands;

import java.time.LocalDate;
import java.util.List;

public record UpdateBookCommand(
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
