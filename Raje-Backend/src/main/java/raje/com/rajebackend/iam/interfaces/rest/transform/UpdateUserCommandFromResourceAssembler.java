package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.commands.UpdateUserCommand;
import raje.com.rajebackend.iam.interfaces.rest.resource.UpdateUserResource;

/**
 * Assembler class to convert UpdateUserResource into UpdateUserCommand.
 */
public class UpdateUserCommandFromResourceAssembler {

    public static UpdateUserCommand toCommandFromResource(UpdateUserResource resource) {
        return new UpdateUserCommand(
                resource.id(),
                resource.firstName(),
                resource.lastName(),
                resource.userName(),
                resource.email(),
                resource.images()
        );
    }
}
