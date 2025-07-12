package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.commands.SignInCommand;
import raje.com.rajebackend.iam.interfaces.rest.resource.AuthenticateUserResource;

/**
 * Assembler class to transform AuthenticateUserResource into a SignInCommand.
 * <p>
 * This is used to decouple the REST layer from the domain layer
 * by mapping external input (DTO) to an internal command object.
 * </p>
 */
public class SignInCommandFromResourceAssembler {

    /**
     * Converts an AuthenticateUserResource (user-provided data) into a SignInCommand.
     *
     * @param resource the resource object containing username/email and password
     * @return a SignInCommand containing the same data for domain processing
     */
    public static SignInCommand toCommandFromResource(AuthenticateUserResource resource) {
        return new SignInCommand(resource.identifier(), resource.password());
    }
}
