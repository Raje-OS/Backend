package raje.com.rajebackend.content.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import raje.com.rajebackend.content.domain.model.valueobjects.ActorId;
import raje.com.rajebackend.content.domain.model.valueobjects.DirectorId;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Entity
public class Serie extends AuditableAbstractAggregateRoot<Serie> {

    @Id
    private String id;

    private String titulo;

    @Embedded
    private DirectorId directorId;

    @ElementCollection
    private List<ActorId> actoresId = new ArrayList<>();

    @ElementCollection
    private List<String> genero = new ArrayList<>();

    private int numTemporadas;

    private int numEpisodios;

    private LocalDate fechaLanzamiento;

    private String idiomaOriginal;

    private String paisOrigen;

    @ElementCollection
    private List<PlatformId> plataformasId = new ArrayList<>();

    private double calificacion;

    @Lob
    private String sinopsis;

    private String imagen;

    protected Serie() {
        this.id = "";
        this.titulo = "";
        this.directorId = new DirectorId("");
        this.fechaLanzamiento = LocalDate.now();
        this.idiomaOriginal = "";
        this.paisOrigen = "";
        this.calificacion = 0.0;
        this.sinopsis = "";
        this.imagen = "";
    }

    public Serie(String id, String titulo, DirectorId directorId, List<ActorId> actoresId,
                 List<String> genero, int numTemporadas, int numEpisodios,
                 LocalDate fechaLanzamiento, String idiomaOriginal, String paisOrigen,
                 List<PlatformId> plataformasId, double calificacion,
                 String sinopsis, String imagen) {

        this.id = id;
        this.titulo = titulo;
        this.directorId = directorId;
        this.actoresId = actoresId;
        this.genero = genero;
        this.numTemporadas = numTemporadas;
        this.numEpisodios = numEpisodios;
        this.fechaLanzamiento = fechaLanzamiento;
        this.idiomaOriginal = idiomaOriginal;
        this.paisOrigen = paisOrigen;
        this.plataformasId = plataformasId;
        this.calificacion = calificacion;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
    }
}
