package raje.com.rajebackend.review.interfaces.rest.transform;

import raje.com.rajebackend.review.domain.model.aggregates.Review;
import raje.com.rajebackend.review.interfaces.rest.resources.ReviewResource;

public class ReviewResourceFromEntityAssembler {

    public static ReviewResource toResourceFromEntity(Review review) {
        return new ReviewResource(
                review.getId(),
                review.getUserId().getId(),
                review.getContenidoId().getId(),
                review.getRating().getValue(),
                review.getText()

        );
    }
}
