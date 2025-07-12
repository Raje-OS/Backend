package raje.com.rajebackend.content.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.content.application.internal.commandservices.UpdateBookServiceImpl;
import raje.com.rajebackend.content.domain.model.commands.UpdateBookCommand;
import raje.com.rajebackend.content.domain.model.queries.GetAllBooksQuery;
import raje.com.rajebackend.content.domain.model.queries.GetBookByIdQuery;
import raje.com.rajebackend.content.domain.model.queries.GetAllBooksOrderedByRatingQuery;
import raje.com.rajebackend.content.domain.services.BookQueryService;
import raje.com.rajebackend.content.interfaces.rest.resources.BookResource;
import raje.com.rajebackend.content.interfaces.rest.transform.BookResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/books", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Books", description = "Operations related to books")
public class BooksController {

    private final BookQueryService queryService;
    private final UpdateBookServiceImpl updateService;

    // Constructor injection for query and command services
    public BooksController(BookQueryService queryService, UpdateBookServiceImpl updateService) {
        this.queryService = queryService;
        this.updateService = updateService;
    }

    /**
     * Endpoint to retrieve all books.
     * @return 200 OK with list of books, or 404 if none found
     */
    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieve all books without any specific order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found"),
            @ApiResponse(responseCode = "404", description = "Books not found")
    })
    public ResponseEntity<List<BookResource>> getAllBooks() {
        var books = queryService.handle(new GetAllBooksQuery()).stream()
                .map(BookResourceFromEntityAssembler::toResourceFromEntity) // Convert entities to resources
                .toList();

        if (books.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(books);
    }

    /**
     * Endpoint to retrieve all books ordered by rating (descending).
     * @return 200 OK with sorted list, or 404 if none found
     */
    @GetMapping("/ordered-by-rating")
    @Operation(summary = "Get all books ordered by rating", description = "Retrieve all books sorted by rating (desc)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Books found"),
            @ApiResponse(responseCode = "404", description = "Books not found")
    })
    public ResponseEntity<List<BookResource>> getAllBooksOrderedByRating() {
        var books = queryService.handle(new GetAllBooksOrderedByRatingQuery()).stream()
                .map(BookResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        if (books.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(books);
    }

    /**
     * Endpoint to retrieve a single book by its ID.
     * @param id book identifier
     * @return 200 OK with book, or 404 if not found
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieve a book by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book found"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookResource> getBookById(@PathVariable String id) {
        var result = queryService.handle(new GetBookByIdQuery(id));
        return result.map(book ->
                        ResponseEntity.ok(BookResourceFromEntityAssembler.toResourceFromEntity(book)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Endpoint to update a book.
     * @param id path ID
     * @param command request body containing the updated fields
     * @return 200 OK with updated book, 400 if ID mismatch, or 404 if not found
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a book", description = "Update the information of a book by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Book updated successfully"),
            @ApiResponse(responseCode = "400", description = "ID mismatch in request"),
            @ApiResponse(responseCode = "404", description = "Book not found")
    })
    public ResponseEntity<BookResource> updateBook(@PathVariable String id, @RequestBody UpdateBookCommand command) {
        if (!id.equals(command.id())) {
            return ResponseEntity.badRequest().build(); // Ensure path ID matches body ID
        }

        var updated = updateService.handle(command); // Perform the update
        return ResponseEntity.ok(BookResourceFromEntityAssembler.toResourceFromEntity(updated));
    }
}
