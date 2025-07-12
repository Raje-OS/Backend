package raje.com.rajebackend.platform.interfaces.rest.transform;

import raje.com.rajebackend.platform.domain.model.commands.SignUpPlatformCommand;
import raje.com.rajebackend.platform.interfaces.rest.resources.SignUpPlatformResource;

public class SignUpPlatformCommandFromResourceAssembler {
    public static SignUpPlatformCommand toCommandFromResource(SignUpPlatformResource resource) {
        return new SignUpPlatformCommand(
                resource.id(),
                resource.nombre(),
                resource.descripcion(),
                resource.imagen(),   // ✅ imagen va en cuarta posición
                resource.email(),    // ✅ email quinta
                resource.password()
        );
    }
}