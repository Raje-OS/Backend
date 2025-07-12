package raje.com.rajebackend.review.domain.services;

import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.domain.model.queries.*;

import java.util.List;

public interface ReviewQueryService {
    List<Review> handle(GetAllReviewsQuery query);
    List<Review> handle(GetReviewsByUserIdQuery query);
    List<Review> handle(GetReviewsByContentIdQuery query);
}
