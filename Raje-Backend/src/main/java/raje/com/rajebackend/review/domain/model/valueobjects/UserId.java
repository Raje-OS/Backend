package raje.com.rajebackend.review.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserId {
    private String UserId;

    protected UserId() {}

    public UserId(String UserId) {
        if (UserId == null || UserId.isBlank()) throw new IllegalArgumentException("User ID cannot be blank");
        this.UserId = UserId;
    }

    public String getId() {
        return UserId;
    }
}
