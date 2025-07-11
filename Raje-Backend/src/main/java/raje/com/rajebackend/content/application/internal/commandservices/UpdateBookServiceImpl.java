package raje.com.rajebackend.content.application.internal.commandservices;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import raje.com.rajebackend.content.domain.model.aggregates.Book;
import raje.com.rajebackend.content.domain.model.commands.UpdateBookCommand;
import raje.com.rajebackend.content.domain.model.valueobjects.AuthorId;
import raje.com.rajebackend.content.domain.model.valueobjects.LibraryId;
import raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories.BookRepository;

@Service
public class UpdateBookServiceImpl {

    private final BookRepository bookRepository;

    public UpdateBookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional
    public Book handle(UpdateBookCommand command) {
        var book = bookRepository.findById(command.id())
                .orElseThrow(() -> new IllegalArgumentException("Book not found with ID: " + command.id()));

        book.setTitulo(command.titulo());
        book.setAutorId(new AuthorId(command.autorId()));
        book.setGenero(command.genero());
        book.setNumPaginas(command.numPaginas());
        book.setFechaPublicacion(command.fechaPublicacion());
        book.setIdiomaOriginal(command.idiomaOriginal());
        book.setPaisOrigen(command.paisOrigen());
        book.setEditorial(command.editorial());
        book.setIsbn(command.isbn());
        book.setCalificacion(command.calificacion());
        book.setSinopsis(command.sinopsis());
        book.setImagen(command.imagen());

        book.getLibreriasId().clear();
        book.getLibreriasId().addAll(command.libreriasId().stream().map(LibraryId::new).toList());

        return bookRepository.save(book);
    }
}
