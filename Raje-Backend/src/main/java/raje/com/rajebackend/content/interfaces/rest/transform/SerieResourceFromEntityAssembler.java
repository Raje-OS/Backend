package raje.com.rajebackend.content.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.aggregates.Serie;
import raje.com.rajebackend.content.interfaces.rest.resources.SerieResource;

public class SerieResourceFromEntityAssembler {

    public static SerieResource toResourceFromEntity(Serie entity) {
        return new SerieResource(
                entity.getId(),
                entity.getTitulo(),
                entity.getDirectorId().getDirectorId(),
                entity.getActoresId().stream()
                        .map(a -> a.getActorId())
                        .toList(),
                entity.getGenero(),
                entity.getNumTemporadas(),
                entity.getNumEpisodios(),
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
