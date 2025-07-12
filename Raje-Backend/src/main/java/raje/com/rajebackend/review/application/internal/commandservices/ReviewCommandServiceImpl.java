package raje.com.rajebackend.review.application.internal.commandservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.domain.model.commands.CreateReviewCommand;
import raje.com.rajebackend.review.domain.model.commands.UpdateReviewCommand;
import raje.com.rajebackend.review.domain.services.ReviewCommandService;
import raje.com.rajebackend.review.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.NoSuchElementException;

@Service
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    public ReviewCommandServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public Review handle(CreateReviewCommand command) {
        Review review = new Review(command);
        return reviewRepository.save(review);
    }

    @Override
    public Review handle(String reviewId, UpdateReviewCommand command) {
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new NoSuchElementException("Review with ID " + reviewId + " not found"));

        review.update(command); // usa el método del aggregate
        return reviewRepository.save(review);
    }

    @Override
    public void handleDelete(String reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            throw new NoSuchElementException("Review with ID " + reviewId + " not found");
        }
        reviewRepository.deleteById(reviewId);
    }

}
