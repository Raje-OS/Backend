package raje.com.rajebackend.content.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public class ActorId {
    private String actorId;

    protected ActorId() {}

    public ActorId(String actorId) {
        this.actorId = actorId;
    }

    public String getActorId() {
        return actorId;
    }
}
