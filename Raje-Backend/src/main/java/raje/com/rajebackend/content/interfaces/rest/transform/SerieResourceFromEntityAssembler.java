package raje.com.rajebackend.content.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.aggregates.Serie;
import raje.com.rajebackend.content.interfaces.rest.resources.SerieResource;

/**
 * Assembler class that transforms a Serie domain entity into a SerieResource DTO.
 * This provides a clean separation between the domain model and the REST API response format.
 */
public class SerieResourceFromEntityAssembler {

    /**
     * Converts a Serie entity into a SerieResource for use in REST API responses.
     *
     * @param entity the Serie domain entity
     * @return a corresponding SerieResource DTO
     */
    public static SerieResource toResourceFromEntity(Serie entity) {
        return new SerieResource(
                entity.getId(),
                entity.getTitulo(),
                entity.getDirectorId().getDirectorId(),
                entity.getActoresId().stream()
                        .map(a -> a.getActorId()) // Extract each actor's ID
                        .toList(),
                entity.getGenero(),
                entity.getNumTemporadas(),
                entity.getNumEpisodios(),
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
