package raje.com.rajebackend.content.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.content.domain.model.aggregates.Serie;
import raje.com.rajebackend.content.domain.model.commands.UpdateSerieCommand;
import raje.com.rajebackend.content.domain.model.valueobjects.ActorId;
import raje.com.rajebackend.content.domain.model.valueobjects.DirectorId;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories.SerieRepository;

@Service
public class UpdateSerieServiceImpl {

    private final SerieRepository serieRepository;

    public UpdateSerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    @Transactional
    public Serie handle(UpdateSerieCommand command) {
        var serie = serieRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Serie not found with ID: " + command.id()));

        serie.setTitulo(command.titulo());
        serie.setDirectorId(new DirectorId(command.directorId()));
        serie.getActoresId().clear();
        serie.getActoresId().addAll(command.actoresId().stream().map(ActorId::new).toList());
        serie.setGenero(command.genero());
        serie.setNumTemporadas(command.numTemporadas());
        serie.setNumEpisodios(command.numEpisodios());
        serie.setFechaLanzamiento(command.fechaLanzamiento());
        serie.setIdiomaOriginal(command.idiomaOriginal());
        serie.setPaisOrigen(command.paisOrigen());
        serie.getPlataformasId().clear();
        serie.getPlataformasId().addAll(command.plataformasId().stream().map(PlatformId::new).toList());
        serie.setCalificacion(command.calificacion());
        serie.setSinopsis(command.sinopsis());
        serie.setImagen(command.imagen());

        return serieRepository.save(serie);
    }
}
