package raje.com.rajebackend.iam.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.domain.model.queries.GetAllUsersQuery;
import raje.com.rajebackend.iam.domain.model.queries.GetUserByIdQuery;
import raje.com.rajebackend.iam.domain.services.UserQueryService;
import raje.com.rajebackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;

    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.id());
    }
}
