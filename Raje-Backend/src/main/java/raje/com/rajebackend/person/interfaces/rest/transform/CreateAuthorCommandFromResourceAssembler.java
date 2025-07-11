package raje.com.rajebackend.person.interfaces.rest.transform;

import raje.com.rajebackend.person.domain.model.commands.CreateAuthorCommand;
import raje.com.rajebackend.person.interfaces.rest.resources.CreateAuthorResource;

public class CreateAuthorCommandFromResourceAssembler {

    public static CreateAuthorCommand toCommandFromResource(CreateAuthorResource resource) {
        return new CreateAuthorCommand(

                resource.id(),
                resource.nombre(),
                resource.descripcion(),
                resource.fechaNacimiento(),
                resource.ciudad_origen(),
                resource.imagen()
        );
    }
}
