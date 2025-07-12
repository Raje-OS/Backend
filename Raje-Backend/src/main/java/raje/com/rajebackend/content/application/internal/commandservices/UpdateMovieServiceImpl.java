package raje.com.rajebackend.content.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.content.domain.model.aggregates.Movie;
import raje.com.rajebackend.content.domain.model.commands.UpdateMovieCommand;
import raje.com.rajebackend.content.domain.model.valueobjects.ActorId;
import raje.com.rajebackend.content.domain.model.valueobjects.DirectorId;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories.MovieRepository;

import java.util.stream.Collectors;

@Service
public class UpdateMovieServiceImpl {

    private final MovieRepository movieRepository;

    // Constructor injecting the MovieRepository dependency
    public UpdateMovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    /**
     * Handles the update of a Movie entity using data from the update command.
     * @param command the command containing updated movie data
     * @return the updated Movie entity
     */
    @Transactional
    public Movie handle(UpdateMovieCommand command) {
        // Retrieve the movie by its ID, or throw an exception if not found
        var movie = movieRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + command.id()));

        // Update the basic fields
        movie.setTitulo(command.titulo());
        movie.setDirectorId(new DirectorId(command.directorId()));

        // Update actor IDs
        movie.getActoresId().clear();
        movie.getActoresId().addAll(command.actoresId().stream().map(ActorId::new).toList());

        // Update additional metadata
        movie.setGenero(command.genero());
        movie.setDuracion(command.duracion());
        movie.setFechaLanzamiento(command.fechaLanzamiento());
        movie.setIdiomaOriginal(command.idiomaOriginal());
        movie.setPaisOrigen(command.paisOrigen());

        // Update platform IDs
        movie.getPlataformasId().clear();
        movie.getPlataformasId().addAll(command.plataformasId().stream().map(PlatformId::new).toList());

        // Update rating, synopsis and image
        movie.setCalificacion(command.calificacion());
        movie.setSinopsis(command.sinopsis());
        movie.setImagen(command.imagen());

        // Save and return the updated movie
        return movieRepository.save(movie);
    }
}
