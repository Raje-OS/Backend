package raje.com.rajebackend.library.interfaces.rest.transform;

import raje.com.rajebackend.library.domain.model.aggregates.Library;
import raje.com.rajebackend.library.domain.model.valueobjects.Location;
import raje.com.rajebackend.library.interfaces.rest.resources.LibraryResource;

import java.util.List;

public class LibraryResourceFromEntityAssembler {

    public static LibraryResource toResourceFromEntity(Library entity) {
        return new LibraryResource(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getImagen(),
                entity.getCredential().email(),
                entity.getCredential().password(),
                entity.getUbicaciones().stream()
                        .map(loc -> new LibraryResource.LocationResource(loc.getLat(), loc.getLng(), loc.getDireccion()))
                        .toList()
        );
    }
}
