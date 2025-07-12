package raje.com.rajebackend.review.interfaces.rest.transform;

import raje.com.rajebackend.review.domain.model.commands.CreateReviewCommand;
import raje.com.rajebackend.review.interfaces.rest.resources.CreateReviewResource;

public class CreateReviewCommandFromResourceAssembler {

    public static CreateReviewCommand toCommandFromResource(CreateReviewResource resource) {
        return new CreateReviewCommand(
                resource.id(),
                resource.userId(),
                resource.contenidoId(),
                resource.rating(),
                resource.text()
        );
    }
}
