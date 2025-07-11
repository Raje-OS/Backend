package raje.com.rajebackend.content.domain.model.commands;

import java.time.LocalDate;
import java.util.List;

public record UpdateSerieCommand(
        String id,
        String titulo,
        String directorId,
        List<String> actoresId,
        List<String> genero,
        int numTemporadas,
        int numEpisodios,
        LocalDate fechaLanzamiento,
        String idiomaOriginal,
        String paisOrigen,
        List<String> plataformasId,
        double calificacion,
        String sinopsis,
        String imagen
) {}
