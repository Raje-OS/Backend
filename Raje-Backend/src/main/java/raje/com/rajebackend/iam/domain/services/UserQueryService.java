package raje.com.rajebackend.iam.domain.services;

import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.domain.model.queries.GetAllUsersQuery;
import raje.com.rajebackend.iam.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    List<User> handle(GetAllUsersQuery query);

    Optional<User> handle(GetUserByIdQuery query);
}
