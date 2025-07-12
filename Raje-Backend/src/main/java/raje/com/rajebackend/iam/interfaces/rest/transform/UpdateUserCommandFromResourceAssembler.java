package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.commands.UpdateUserCommand;
import raje.com.rajebackend.iam.interfaces.rest.resource.UpdateUserResource;

/**
 * Assembler class to transform UpdateUserResource into an UpdateUserCommand.
 * <p>
 * This transformation is used to map HTTP request data into a command
 * object for updating user information in the application layer.
 * </p>
 */
public class UpdateUserCommandFromResourceAssembler {

    /**
     * Maps an UpdateUserResource to an UpdateUserCommand.
     *
     * @param resource the REST resource containing user update data
     * @return a command object to be handled by the application service
     */
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
