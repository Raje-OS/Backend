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
