package raje.com.rajebackend.shared.domain.model.valueobjects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public record EmailAddress(String email) {

    public EmailAddress{
        if (email == null ) {
            throw new IllegalArgumentException("Email address cannot be null");
        }
        if ( email.length() > 50 ) {
            throw new IllegalArgumentException("Email address is too long");
        }
        if (!email.matches("^[\\w-.]+@[\\w-]+\\.[a-zA-Z]{2,}$")) {
            throw new IllegalArgumentException("Invalid email address format");
        }


    }

}
