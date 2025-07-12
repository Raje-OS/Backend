package raje.com.rajebackend.review.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContentId {
    private String contenidoId;

    protected ContentId() {}

    public ContentId(String contenidoId) {
        if (contenidoId == null || contenidoId.isBlank()) throw new IllegalArgumentException("Content ID cannot be blank");
        this.contenidoId = contenidoId;
    }

    public String getId() {
        return contenidoId;
    }
}
