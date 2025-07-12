package raje.com.rajebackend.review.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.domain.model.queries.GetAllReviewsQuery;
import raje.com.rajebackend.review.domain.model.queries.GetReviewsByContentIdQuery;
import raje.com.rajebackend.review.domain.model.queries.GetReviewsByUserIdQuery;
import raje.com.rajebackend.review.domain.services.ReviewQueryService;
import raje.com.rajebackend.review.infrastructure.persistence.jpa.repositories.ReviewRepository;

import java.util.List;

@Service
public class ReviewQueryServiceImpl implements ReviewQueryService {

    private final ReviewRepository reviewRepository;

    public ReviewQueryServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<Review> handle(GetAllReviewsQuery query) {
        return reviewRepository.findAll();
    }

    @Override
    public List<Review> handle(GetReviewsByUserIdQuery query) {
        return reviewRepository.findByUserId(query.userId());
    }

    @Override
    public List<Review> handle(GetReviewsByContentIdQuery query) {
        return reviewRepository.findByContenidoId(query.contenidoId());
    }
}
