package raje.com.rajebackend.review.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class UserId {
    private String UserId;

    protected UserId() {}

    public UserId(String UserId) {

        this.UserId = UserId;
    }

    public String getId() {
        return UserId;
    }
}
