package raje.com.rajebackend.iam.interfaces.rest.transform;

import org.apache.commons.lang3.tuple.ImmutablePair;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.interfaces.rest.resource.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {

    public static AuthenticatedUserResource toResourceFromEntity(ImmutablePair<User, String> pair) {
        var user = pair.getLeft();
        var token = pair.getRight();

        return new AuthenticatedUserResource(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUserName(),
                user.getEmail(),
                user.getPlatforms().stream()
                        .map(PlatformId::getPlatformId)
                        .toList(),
                user.getImages(),
                token
        );
    }
}
