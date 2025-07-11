package raje.com.rajebackend.person.domain.model.valueobjects;
import jakarta.persistence.Embeddable;

@Embeddable
public record City(String ciudad_origen) {

    public String descripcionCompleta() {
        return ciudad_origen ;
    }
}
