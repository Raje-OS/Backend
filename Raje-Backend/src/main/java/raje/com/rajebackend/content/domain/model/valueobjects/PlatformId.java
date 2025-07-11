package raje.com.rajebackend.content.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class PlatformId {
    private String platformId;

    protected PlatformId() {}

    public PlatformId(String platformId) {
        this.platformId = platformId;
    }

    public String getPlatformId() {
        return platformId;
    }
}
