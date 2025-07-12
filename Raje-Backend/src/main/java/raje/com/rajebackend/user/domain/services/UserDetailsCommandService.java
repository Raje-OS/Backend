package raje.com.rajebackend.user.domain.services;

import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;
import raje.com.rajebackend.user.domain.model.commands.CreateUserDetailsCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateUserDetailsCommand;

public interface UserDetailsCommandService {
    UserDetails handle(CreateUserDetailsCommand command);
    UserDetails handle(String id, UpdateUserDetailsCommand command);
    UserDetails handle(String id, UserDetails updatedDetails);

}
