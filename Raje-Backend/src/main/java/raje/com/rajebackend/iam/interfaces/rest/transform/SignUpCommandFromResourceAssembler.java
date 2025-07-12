package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.commands.SignUpCommand;
import raje.com.rajebackend.iam.interfaces.rest.resource.SignUpResource;

/**
 * Assembler class to transform CreateUserResource into SignUpCommand.
 */
public class SignUpCommandFromResourceAssembler {

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