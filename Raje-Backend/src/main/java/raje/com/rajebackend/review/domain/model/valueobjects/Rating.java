package raje.com.rajebackend.review.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    private int rating;

    protected Rating() {}

    public Rating(int rating) {
        if (rating < 0 || rating > 5)
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        this.rating = rating;
    }

    public int getValue() {
        return rating;
    }
}
