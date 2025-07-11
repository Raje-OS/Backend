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

    public UpdateMovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public Movie handle(UpdateMovieCommand command) {
        var movie = movieRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Movie not found with ID: " + command.id()));

        // Setters directos
        movie.setTitulo(command.titulo());
        movie.setDirectorId(new DirectorId(command.directorId()));
        movie.getActoresId().clear();
        movie.getActoresId().addAll(command.actoresId().stream().map(ActorId::new).toList());
        movie.setGenero(command.genero());
        movie.setDuracion(command.duracion());
        movie.setFechaLanzamiento(command.fechaLanzamiento());
        movie.setIdiomaOriginal(command.idiomaOriginal());
        movie.setPaisOrigen(command.paisOrigen());
        movie.getPlataformasId().clear();
        movie.getPlataformasId().addAll(command.plataformasId().stream().map(PlatformId::new).toList());
        movie.setCalificacion(command.calificacion());
        movie.setSinopsis(command.sinopsis());
        movie.setImagen(command.imagen());

        return movieRepository.save(movie);
    }
}
