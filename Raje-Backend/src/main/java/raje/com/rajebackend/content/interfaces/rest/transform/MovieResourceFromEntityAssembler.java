package raje.com.rajebackend.content.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.aggregates.Movie;
import raje.com.rajebackend.content.interfaces.rest.resources.MovieResource;

public class MovieResourceFromEntityAssembler {

    public static MovieResource toResourceFromEntity(Movie entity) {
        return new MovieResource(
                entity.getId(),
                entity.getTitulo(),
                entity.getDirectorId().getDirectorId(),
                entity.getActoresId().stream()
                        .map(a -> a.getActorId())
                        .toList(),
                entity.getGenero(),
                entity.getDuracion(),
                entity.getFechaLanzamiento(),
                entity.getIdiomaOriginal(),
                entity.getPaisOrigen(),
                entity.getPlataformasId().stream()
                        .map(p -> p.getPlatformId())
                        .toList(),
                entity.getCalificacion(),
                entity.getSinopsis(),
                entity.getImagen()
        );
    }
}
