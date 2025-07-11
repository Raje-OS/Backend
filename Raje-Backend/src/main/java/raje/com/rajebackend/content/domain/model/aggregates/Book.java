package raje.com.rajebackend.content.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import raje.com.rajebackend.content.domain.model.valueobjects.AuthorId;
import raje.com.rajebackend.content.domain.model.valueobjects.LibraryId;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Book extends AuditableAbstractAggregateRoot<Book> {

    @Id
    private String id;

    private String titulo;

    @Embedded
    private AuthorId autorId;

    @ElementCollection
    private List<String> genero = new ArrayList<>();

    private int numPaginas;

    private LocalDate fechaPublicacion;

    private String idiomaOriginal;

    private String paisOrigen;

    private String editorial;

    private String isbn;

    private double calificacion;

    @Lob
    private String sinopsis;

    private String imagen;

    @ElementCollection
    private List<LibraryId> libreriasId = new ArrayList<>();

    protected Book() {
        this.id = "";
        this.titulo = "";
        this.autorId = new AuthorId("");
        this.fechaPublicacion = LocalDate.now();
        this.idiomaOriginal = "";
        this.paisOrigen = "";
        this.editorial = "";
        this.isbn = "";
        this.calificacion = 0.0;
        this.sinopsis = "";
        this.imagen = "";
    }

    public Book(String id, String titulo, AuthorId autorId, List<String> genero, int numPaginas,
                LocalDate fechaPublicacion, String idiomaOriginal, String paisOrigen,
                String editorial, String isbn, double calificacion, String sinopsis,
                String imagen, List<LibraryId> libreriasId) {

        this.id = id;
        this.titulo = titulo;
        this.autorId = autorId;
        this.genero = genero;
        this.numPaginas = numPaginas;
        this.fechaPublicacion = fechaPublicacion;
        this.idiomaOriginal = idiomaOriginal;
        this.paisOrigen = paisOrigen;
        this.editorial = editorial;
        this.isbn = isbn;
        this.calificacion = calificacion;
        this.sinopsis = sinopsis;
        this.imagen = imagen;
        this.libreriasId = libreriasId;
    }
}
