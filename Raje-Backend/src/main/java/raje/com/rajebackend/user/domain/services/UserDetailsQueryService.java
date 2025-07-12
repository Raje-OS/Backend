package raje.com.rajebackend.user.domain.services;

import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;

import java.util.Optional;

public interface UserDetailsQueryService {
    Optional<UserDetails> findByUserId(String userId);
}
