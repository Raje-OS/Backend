package raje.com.rajebackend.content.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.aggregates.Book;
import raje.com.rajebackend.content.interfaces.rest.resources.BookResource;

public class BookResourceFromEntityAssembler {

    public static BookResource toResourceFromEntity(Book entity) {
        return new BookResource(
                entity.getId(),
                entity.getTitulo(),
                entity.getAutorId().getAuthorId(),
                entity.getGenero(),
                entity.getNumPaginas(),
                entity.getFechaPublicacion(),
                entity.getIdiomaOriginal(),
                entity.getPaisOrigen(),
                entity.getEditorial(),
                entity.getIsbn(),
                entity.getCalificacion(),
                entity.getSinopsis(),
                entity.getImagen(),
                entity.getLibreriasId().stream()
                        .map(lib -> lib.getLibraryId())
                        .toList()
        );
    }
}
