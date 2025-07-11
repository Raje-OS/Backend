package raje.com.rajebackend.content.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class DirectorId {
    private String directorId;

    protected DirectorId() {}

    public DirectorId(String directorId) {
        this.directorId = directorId;
    }

    public String getDirectorId() {
        return directorId;
    }
}
