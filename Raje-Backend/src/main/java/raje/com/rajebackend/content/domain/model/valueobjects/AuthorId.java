package raje.com.rajebackend.content.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class AuthorId {
    private String authorId;

    public AuthorId(String authorId) {
        this.authorId = authorId;
    }
}
