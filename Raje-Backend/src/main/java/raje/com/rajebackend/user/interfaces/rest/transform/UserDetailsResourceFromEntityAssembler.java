package raje.com.rajebackend.user.interfaces.rest.transform;

import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;
import raje.com.rajebackend.user.interfaces.rest.resources.UserDetailsResource;

public class UserDetailsResourceFromEntityAssembler {

    public static UserDetailsResource toResourceFromEntity(UserDetails userDetails) {
        return new UserDetailsResource(
                userDetails.getId(),
                userDetails.getUserId(),
                userDetails.getFavorites(),
                userDetails.getViewed()
        );
    }
}
