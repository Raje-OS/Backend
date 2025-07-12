package raje.com.rajebackend.review.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class Rating {
    private int value;

    protected Rating() {}

    public Rating(int value) {
        if (value < 0 || value > 5)
            throw new IllegalArgumentException("Rating must be between 0 and 5");
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
