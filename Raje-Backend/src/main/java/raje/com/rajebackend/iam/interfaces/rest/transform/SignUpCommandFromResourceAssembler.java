package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.commands.SignUpCommand;
import raje.com.rajebackend.iam.interfaces.rest.resource.SignUpResource;

/**
 * Assembler class to transform SignUpResource into a SignUpCommand.
 * <p>
 * This transformation is used to convert external HTTP request data
 * into an internal command object used in the application layer.
 * </p>
 */
public class SignUpCommandFromResourceAssembler {

    /**
     * Maps a SignUpResource object to a SignUpCommand.
     *
     * @param resource the REST resource containing user registration data
     * @return a SignUpCommand to be processed by the command service
     */
    public static SignUpCommand toCommandFromResource(SignUpResource resource) {
        return new SignUpCommand(
                resource.id(),
                resource.firstName(),
                resource.lastName(),
                resource.userName(),
                resource.email(),
                resource.password(),
                resource.platforms(),
                resource.images()
        );
    }
}
