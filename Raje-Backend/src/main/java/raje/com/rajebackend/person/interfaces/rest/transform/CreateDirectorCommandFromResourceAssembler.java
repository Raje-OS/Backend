package raje.com.rajebackend.person.interfaces.rest.transform;

import raje.com.rajebackend.person.domain.model.commands.CreateDirectorCommand;
import raje.com.rajebackend.person.interfaces.rest.resources.CreateDirectorResource;

public class CreateDirectorCommandFromResourceAssembler {
    public static CreateDirectorCommand toCommandFromResource(CreateDirectorResource resource) {
        return new CreateDirectorCommand(
                resource.id(),
                resource.nombre(),
                resource.descripcion(),
                resource.fechaNacimiento(),
                resource.ciudad_origen(),
                resource.imagen()
        );
    }
}
