package raje.com.rajebackend.user.interfaces.rest.transform;

import raje.com.rajebackend.user.domain.model.commands.CreateListCommand;
import raje.com.rajebackend.user.interfaces.rest.resources.CreateListResource;

public class CreateListCommandFromResourceAssembler {

    public static CreateListCommand toCommandFromResource(CreateListResource resource) {
        return new CreateListCommand(
                resource.id(),
                resource.userId(),
                resource.name(),
                resource.description(),
                resource.list_content()
        );
    }
}
