package raje.com.rajebackend.person.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record City(String ciudad, String pais) {

    public String descripcionCompleta() {
        return ciudad + ", " + pais;
    }
}
