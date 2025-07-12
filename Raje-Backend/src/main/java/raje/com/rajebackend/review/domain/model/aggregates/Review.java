package raje.com.rajebackend.review.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import raje.com.rajebackend.review.domain.model.commands.CreateReviewCommand;
import raje.com.rajebackend.review.domain.model.commands.UpdateReviewCommand;
import raje.com.rajebackend.review.domain.model.valueobjects.ContentId;
import raje.com.rajebackend.review.domain.model.valueobjects.Rating;
import raje.com.rajebackend.review.domain.model.valueobjects.UserId;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDateTime;

@Getter
@Entity
public class Review extends AuditableAbstractAggregateRoot<Review> {

    @Id
    private String id;

    @Embedded
    private UserId userId;

    @Embedded
    private ContentId contenidoId;

    @Embedded
    private Rating rating;

    private String text;



    protected Review() {
        this.id = "";
        this.userId = new UserId("");
        this.contenidoId = new ContentId("");
        this.rating = new Rating(0); // Puede ser 0 a 5
        this.text = "";

    }


    public Review(CreateReviewCommand command) {
        this.id = command.id();
        this.userId = new UserId(command.userId());
        this.contenidoId = new ContentId(command.contenidoId());
        this.rating = new Rating(command.rating());
        this.text = (command.text() != null) ? command.text().trim() : "";

    }


    public Review update(UpdateReviewCommand command) {
        this.rating = new Rating(command.rating());
        this.text = (command.text() != null) ? command.text().trim() : "";
        return this;
    }
}
