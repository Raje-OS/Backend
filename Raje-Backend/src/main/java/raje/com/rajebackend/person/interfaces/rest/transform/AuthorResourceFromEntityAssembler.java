package raje.com.rajebackend.person.interfaces.rest.transform;

import raje.com.rajebackend.person.domain.model.aggregates.Author;
import raje.com.rajebackend.person.interfaces.rest.resources.AuthorResource;

public class AuthorResourceFromEntityAssembler {
    public static AuthorResource toResourceFromEntity(Author author) {
        return new AuthorResource(
                author.getId(),
                author.getNombre(),
                author.getDescripcion(),
                author.getFechaNacimiento(),
                author.getCiudadOrigen().ciudad_origen(),
                author.getImagen()
        );
    }
}
