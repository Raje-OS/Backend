package raje.com.rajebackend.review.interfaces.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import raje.com.rajebackend.review.application.internal.commandservices.ReviewCommandServiceImpl;
import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.domain.model.commands.CreateReviewCommand;
import raje.com.rajebackend.review.domain.model.commands.UpdateReviewCommand;
import raje.com.rajebackend.review.domain.model.queries.*;
import raje.com.rajebackend.review.domain.services.ReviewQueryService;
import raje.com.rajebackend.review.interfaces.rest.resources.CreateReviewResource;
import raje.com.rajebackend.review.interfaces.rest.resources.ReviewResource;
import raje.com.rajebackend.review.interfaces.rest.transform.CreateReviewCommandFromResourceAssembler;
import raje.com.rajebackend.review.interfaces.rest.transform.ReviewResourceFromEntityAssembler;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing user reviews.
 * Provides endpoints for creating, retrieving, updating, and deleting reviews.
 */
@RestController
@RequestMapping(value = "/api/v1/reviews", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Reviews", description = "Operations related to user reviews")
public class ReviewController {

    private final ReviewQueryService queryService;
    private final ReviewCommandServiceImpl commandService;

    public ReviewController(ReviewQueryService queryService, ReviewCommandServiceImpl commandService) {
        this.queryService = queryService;
        this.commandService = commandService;
    }

    /**
     * Retrieves all reviews.
     *
     * @return a list of all reviews
     */
    @GetMapping
    @Operation(summary = "Get all reviews", description = "Retrieve all reviews")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "404", description = "No reviews found")
    })
    public ResponseEntity<List<ReviewResource>> getAllReviews() {
        var reviews = queryService.handle(new GetAllReviewsQuery());
        if (reviews.isEmpty()) return ResponseEntity.notFound().build();

        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    /**
     * Retrieves reviews by a specific user ID.
     *
     * @param userId the ID of the user
     * @return a list of reviews made by the user
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get reviews by user ID", description = "Retrieve all reviews by a specific user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "404", description = "No reviews found")
    })
    public ResponseEntity<List<ReviewResource>> getReviewsByUser(@PathVariable String userId) {
        var reviews = queryService.handle(new GetReviewsByUserIdQuery(userId));
        if (reviews.isEmpty()) return ResponseEntity.notFound().build();

        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    /**
     * Retrieves reviews by content ID.
     *
     * @param contenidoId the ID of the content
     * @return a list of reviews associated with the content
     */
    @GetMapping("/content/{contenidoId}")
    @Operation(summary = "Get reviews by content ID", description = "Retrieve all reviews for a specific content")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Reviews found"),
            @ApiResponse(responseCode = "404", description = "No reviews found")
    })
    public ResponseEntity<List<ReviewResource>> getReviewsByContent(@PathVariable String contenidoId) {
        var reviews = queryService.handle(new GetReviewsByContentIdQuery(contenidoId));
        if (reviews.isEmpty()) return ResponseEntity.notFound().build();

        var resources = reviews.stream()
                .map(ReviewResourceFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(resources);
    }

    /**
     * Creates a new review.
     *
     * @param resource the resource containing review data
     * @return the created review
     */
    @PostMapping
    @Operation(summary = "Create a review", description = "Register a new user review")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Review created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid review data")
    })
    public ResponseEntity<ReviewResource> createReview(@Valid @RequestBody CreateReviewResource resource) {
        var command = CreateReviewCommandFromResourceAssembler.toCommandFromResource(resource);
        var created = commandService.handle(command);
        return ResponseEntity.ok(ReviewResourceFromEntityAssembler.toResourceFromEntity(created));
    }

    /**
     * Updates an existing review by ID.
     *
     * @param id      the ID of the review to update
     * @param command the updated review data
     * @return the updated review
     */
    @PutMapping("/{id}")
    @Operation(summary = "Update a review", description = "Update an existing review by ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Review updated successfully"),
            @ApiResponse(responseCode = "400", description = "ID mismatch or invalid data"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    public ResponseEntity<ReviewResource> updateReview(@PathVariable String id,
                                                       @Valid @RequestBody UpdateReviewCommand command) {
        var updated = commandService.handle(id, command);
        return ResponseEntity.ok(ReviewResourceFromEntityAssembler.toResourceFromEntity(updated));
    }

    /**
     * Deletes a review by its ID.
     *
     * @param id the ID of the review to delete
     * @return a response indicating the operation result
     */
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete review by ID", description = "Deletes a review given its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Review deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Review not found")
    })
    public ResponseEntity<Void> deleteReview(@PathVariable String id) {
        commandService.handleDelete(id);
        return ResponseEntity.ok().build();
    }
}
