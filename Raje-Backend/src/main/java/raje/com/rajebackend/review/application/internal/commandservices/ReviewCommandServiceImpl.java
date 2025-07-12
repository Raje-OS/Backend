package raje.com.rajebackend.review.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.domain.model.commands.CreateReviewCommand;
import raje.com.rajebackend.review.domain.model.commands.UpdateReviewCommand;
import raje.com.rajebackend.review.domain.services.ReviewCommandService;
import raje.com.rajebackend.review.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.NoSuchElementException;

/**
 * Service class that handles review creation, updating, and deletion commands.
 * Implements the ReviewCommandService interface to encapsulate the command logic
 * related to the Review aggregate.
 */
@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Handles the creation of a new review.
     *
     * @param command the command object containing review data
     * @return the created Review aggregate
     */
    @Override
    public Review handle(CreateReviewCommand command) {
        Review review = new Review(command);
        return reviewRepository.save(review);
    }

    /**
     * Handles updating an existing review.
     *
     * @param reviewId the ID of the review to update
     * @param command the command object with updated fields
     * @return the updated Review aggregate
     * @throws NoSuchElementException if the review ID does not exist
     */
    @Override
    public Review handle(String reviewId, UpdateReviewCommand command) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("Review with ID " + reviewId + " not found"));

        review.update(command);
        return reviewRepository.save(review);
    }

    /**
     * Handles deletion of a review by ID.
     *
     * @param reviewId the ID of the review to delete
     * @throws NoSuchElementException if the review ID does not exist
     */
    @Override
    public void handleDelete(String reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new NoSuchElementException("Review with ID " + reviewId + " not found");
        }
        reviewRepository.deleteById(reviewId);
    }
}
