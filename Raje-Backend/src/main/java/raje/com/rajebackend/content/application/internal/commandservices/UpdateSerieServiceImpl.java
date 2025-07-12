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

    // Constructor that injects the SerieRepository dependency
    public UpdateSerieServiceImpl(SerieRepository serieRepository) {
        this.serieRepository = serieRepository;
    }

    /**
     * Handles the update of a Serie entity using the data provided in the command.
     * @param command the update command with new values for the Serie
     * @return the updated Serie entity
     */
    @Transactional
    public Serie handle(UpdateSerieCommand command) {
        // Retrieve the serie by ID, or throw an exception if not found
        var serie = serieRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Serie not found with ID: " + command.id()));

        // Update basic fields
        serie.setTitulo(command.titulo());
        serie.setDirectorId(new DirectorId(command.directorId()));

        // Update list of actor IDs
        serie.getActoresId().clear();
        serie.getActoresId().addAll(command.actoresId().stream().map(ActorId::new).toList());

        // Update genre and episode information
        serie.setGenero(command.genero());
        serie.setNumTemporadas(command.numTemporadas());
        serie.setNumEpisodios(command.numEpisodios());

        // Update release date, language and country
        serie.setFechaLanzamiento(command.fechaLanzamiento());
        serie.setIdiomaOriginal(command.idiomaOriginal());
        serie.setPaisOrigen(command.paisOrigen());

        // Update platform IDs
        serie.getPlataformasId().clear();
        serie.getPlataformasId().addAll(command.plataformasId().stream().map(PlatformId::new).toList());

        // Update rating, synopsis and image
        serie.setCalificacion(command.calificacion());
        serie.setSinopsis(command.sinopsis());
        serie.setImagen(command.imagen());

        // Save and return the updated serie
        return serieRepository.save(serie);
    }
}
