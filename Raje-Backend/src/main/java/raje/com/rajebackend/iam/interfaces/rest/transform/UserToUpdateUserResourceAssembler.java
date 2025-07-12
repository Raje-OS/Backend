package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.interfaces.rest.resource.UpdateUserResource;

/**
 * Assembler class to transform a {@link User} domain entity into an {@link UpdateUserResource}.
 * <p>
 * This transformation is typically used to expose user data in a format suitable
 * for REST-based update operations on the client side.
 * </p>
 */
public class UserToUpdateUserResourceAssembler {

    /**
     * Maps a {@link User} domain entity to an {@link UpdateUserResource}.
     *
     * @param user the domain user entity to transform
     * @return a resource representation of the user for update operations
     */
    public static UpdateUserResource toResourceFromEntity(User user) {
        return new UpdateUserResource(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getImages()
        );
    }
}
