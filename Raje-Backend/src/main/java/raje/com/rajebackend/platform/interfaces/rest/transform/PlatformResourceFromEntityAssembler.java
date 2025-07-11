package raje.com.rajebackend.platform.interfaces.rest.transform;

import raje.com.rajebackend.platform.domain.model.aggregates.Platform;
import raje.com.rajebackend.platform.interfaces.rest.resources.PlatformResource;

public class PlatformResourceFromEntityAssembler {

    public static PlatformResource toResourceFromEntity(Platform platform) {
        return new PlatformResource(
                platform.getId(),
                platform.getNombre(),
                platform.getDescripcion(),
                platform.getImagen(),
                platform.getCredential().email(),
                platform.getCredential().password()
        );
    }
}
