package raje.com.rajebackend.content.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class LibraryId {
    private String libraryId;

    public LibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
}
