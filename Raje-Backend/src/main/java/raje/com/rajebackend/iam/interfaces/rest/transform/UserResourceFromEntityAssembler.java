package raje.com.rajebackend.iam.interfaces.rest.transform;

import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.interfaces.rest.resource.UserResource;

/**
 * Assembler class to convert User domain entity into a UserResource DTO.
 */
public class UserResourceFromEntityAssembler {

    public static UserResource toResourceFromEntity(User entity) {
        return new UserResource(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getUserName(),
                entity.getEmail(),
                entity.getImages(),
                entity.getPlatforms().stream()
                        .map(PlatformId::getPlatformId)
                        .toList()
        );
    }
}
