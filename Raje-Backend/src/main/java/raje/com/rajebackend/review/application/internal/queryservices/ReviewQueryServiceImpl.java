package raje.com.rajebackend.review.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.domain.model.queries.GetAllReviewsQuery;
import raje.com.rajebackend.review.domain.model.queries.GetReviewsByContentIdQuery;
import raje.com.rajebackend.review.domain.model.queries.GetReviewsByUserIdQuery;
import raje.com.rajebackend.review.domain.services.ReviewQueryService;
import raje.com.rajebackend.review.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.List;

/**
 * Service implementation for handling review queries.
 * Provides methods to retrieve reviews based on different criteria
 * such as all reviews, by user ID, or by content ID.
 */
@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    /**
     * Retrieves all reviews from the repository.
     *
     * @param query the query object (unused but conforms to interface)
     * @return a list of all reviews
     */
    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    /**
     * Retrieves reviews submitted by a specific user.
     *
     * @param query the query containing the user ID
     * @return a list of reviews associated with the given user ID
     */
    @Override
    public List<Review> handle(GetReviewsByUserIdQuery query) {
        return reviewRepository.findByUserId(query.userId());
    }

    /**
     * Retrieves reviews associated with a specific content item.
     *
     * @param query the query containing the content ID
     * @return a list of reviews related to the given content ID
     */
    @Override
    public List<Review> handle(GetReviewsByContentIdQuery query) {
        return reviewRepository.findByContenidoId(query.contenidoId());
    }
}
