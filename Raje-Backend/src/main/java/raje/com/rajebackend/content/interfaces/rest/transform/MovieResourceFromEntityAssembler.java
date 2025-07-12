package raje.com.rajebackend.content.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.aggregates.Movie;
import raje.com.rajebackend.content.interfaces.rest.resources.MovieResource;

/**
 * Assembler class that transforms a Movie domain entity into a MovieResource DTO.
 * This helps separate the internal domain model from the external API representation.
 */
public class MovieResourceFromEntityAssembler {

    /**
     * Converts a Movie entity into a MovieResource to be returned in REST API responses.
     *
     * @param entity the Movie domain entity
     * @return a corresponding MovieResource DTO
     */
    public static MovieResource toResourceFromEntity(Movie entity) {
        return new MovieResource(
                entity.getId(),
                entity.getTitulo(),
                entity.getDirectorId().getDirectorId(),
                entity.getActoresId().stream()
                        .map(a -> a.getActorId()) // Extract each actor's ID
                        .toList(),
                entity.getGenero(),
                entity.getDuracion(),
                entity.getFechaLanzamiento(),
                entity.getIdiomaOriginal(),
                entity.getPaisOrigen(),
                entity.getPlataformasId().stream()
                        .map(p -> p.getPlatformId()) // Extract each platform's ID
                        .toList(),
                entity.getCalificacion(),
                entity.getSinopsis(),
                entity.getImagen()
        );
    }
}
