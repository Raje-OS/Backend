package raje.com.rajebackend.user.application.internal.queriesservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;
import raje.com.rajebackend.user.domain.services.UserDetailsQueryService;
import raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories.UserDetailsRepository;

import java.util.Optional;

@Service
public class UserDetailsQueryServiceImpl implements UserDetailsQueryService {

    private final UserDetailsRepository userDetailsRepository;

    public UserDetailsQueryServiceImpl(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    public Optional<UserDetails> findByUserId(String userId) {
        return userDetailsRepository.findByUserId(userId);
    }
}
