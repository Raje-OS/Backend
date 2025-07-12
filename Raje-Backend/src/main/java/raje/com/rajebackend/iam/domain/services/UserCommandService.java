package raje.com.rajebackend.iam.domain.services;

import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.domain.model.commands.SignUpCommand;
import raje.com.rajebackend.iam.domain.model.commands.SignInCommand;
import raje.com.rajebackend.iam.domain.model.commands.UpdateUserCommand;

public interface UserCommandService {

    User handle(SignUpCommand command);

    User handle(SignInCommand command);

    User handle(String id, UpdateUserCommand command);

    String generateToken(String username);
}
