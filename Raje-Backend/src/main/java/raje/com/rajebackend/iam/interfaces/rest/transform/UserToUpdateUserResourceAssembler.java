package raje.com.rajebackend.iam.interfaces.rest.transform;


import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.interfaces.rest.resource.UpdateUserResource;

public class UserToUpdateUserResourceAssembler {

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
