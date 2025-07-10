package raje.com.rajebackend.person.interfaces.rest.transform;

import raje.com.rajebackend.person.domain.model.commands.CreateActorCommand;
import raje.com.rajebackend.person.interfaces.rest.resources.CreateActorResource;

public class CreateActorCommandFromResourceAssembler {
    public static CreateActorCommand toCommandFromResource(CreateActorResource resource) {
        return new CreateActorCommand(
                resource.nombre(),
                resource.descripcion(),
                resource.fechaNacimiento(),
                resource.ciudad(),
                resource.pais(),
                resource.imagen()
        );
    }
}
