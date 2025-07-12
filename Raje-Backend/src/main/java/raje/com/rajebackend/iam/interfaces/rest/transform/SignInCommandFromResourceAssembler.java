package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.commands.SignInCommand;
import raje.com.rajebackend.iam.interfaces.rest.resource.AuthenticateUserResource;

/**
 * Assembler class to transform AuthenticateUserResource into SignInCommand.
 */
public class SignInCommandFromResourceAssembler {

    public static SignInCommand toCommandFromResource(AuthenticateUserResource resource) {
        return new SignInCommand(resource.identifier(), resource.password());
    }
}