package raje.com.rajebackend.user.interfaces.rest.transform;

import raje.com.rajebackend.user.domain.model.commands.CreateUserDetailsCommand;
import raje.com.rajebackend.user.interfaces.rest.resources.CreateUserDetailsResource;

public class CreateUserDetailsCommandFromResourceAssembler {

    public static CreateUserDetailsCommand toCommandFromResource(CreateUserDetailsResource resource) {
        return new CreateUserDetailsCommand(
                resource.id(),
                resource.userId(),
                resource.favorites(),
                resource.viewed()
        );
    }
}
