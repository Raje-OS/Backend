package raje.com.rajebackend.review.domain.services;

import raje.com.rajebackend.review.domain.model.commands.CreateReviewCommand;
import raje.com.rajebackend.review.domain.model.commands.UpdateReviewCommand;
import raje.com.rajebackend.review.domain.model.aggregates.Review;

public interface ReviewCommandService {
    Review handle(CreateReviewCommand command);
    Review handle(String reviewId, UpdateReviewCommand command);
    void handleDelete(String reviewId);

}
