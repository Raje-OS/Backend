package raje.com.rajebackend.iam.interfaces.rest.transform;

import org.apache.commons.lang3.tuple.ImmutablePair;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.interfaces.rest.resource.AuthenticatedUserResource;

/**
 * Assembler class that transforms a User entity and a JWT token
 * into an AuthenticatedUserResource DTO for API responses.
 */
public class AuthenticatedUserResourceFromEntityAssembler {

    /**
     * Converts a pair of User and JWT token into an AuthenticatedUserResource.
     *
     * @param pair an ImmutablePair where the left is a User and the right is a token
     * @return a populated AuthenticatedUserResource
     */
    public static AuthenticatedUserResource toResourceFromEntity(ImmutablePair<User, String> pair) {
        var user = pair.getLeft();   // The authenticated user entity
        var token = pair.getRight(); // The generated JWT token

        return new AuthenticatedUserResource(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getPlatforms().stream()
                        .map(PlatformId::getPlatformId) // Extract platform IDs
                        .toList(),
                user.getImages(),
                token
        );
    }
}
