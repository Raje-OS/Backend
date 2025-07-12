package raje.com.rajebackend.review.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ContentId {
    private String contenidoId;

    protected ContentId() {}

    public ContentId(String contenidoId) {

        this.contenidoId = contenidoId;
    }

    public String getId() {
        return contenidoId;
    }
}
