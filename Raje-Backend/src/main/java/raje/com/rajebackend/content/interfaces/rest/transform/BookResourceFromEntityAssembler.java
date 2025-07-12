package raje.com.rajebackend.content.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.aggregates.Book;
import raje.com.rajebackend.content.interfaces.rest.resources.BookResource;

/**
 * Assembler class that transforms a Book domain entity into a BookResource DTO.
 * This is used to decouple internal domain models from external REST representations.
 */
public class BookResourceFromEntityAssembler {

    /**
     * Converts a Book entity to a BookResource object to be sent in REST responses.
     *
     * @param entity the Book domain entity
     * @return a corresponding BookResource DTO
     */
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
                        .map(lib -> lib.getLibraryId()) // Extract library IDs from value objects
                        .toList()
        );
    }
}
