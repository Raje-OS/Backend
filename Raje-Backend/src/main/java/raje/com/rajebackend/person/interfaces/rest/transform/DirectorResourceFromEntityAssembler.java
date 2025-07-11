package raje.com.rajebackend.person.interfaces.rest.transform;

import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.interfaces.rest.resources.DirectorResource;

public class DirectorResourceFromEntityAssembler {

    public static DirectorResource toResourceFromEntity(Director director) {
        return new DirectorResource(
                director.getId(),
                director.getNombre(),
                director.getDescripcion(),
                director.getFechaNacimiento(),
                director.getCiudadOrigen().ciudad_origen(),
                director.getImagen()
        );
    }
}
