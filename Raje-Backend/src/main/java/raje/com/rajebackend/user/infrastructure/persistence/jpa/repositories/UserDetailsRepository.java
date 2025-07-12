package raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raje.com.rajebackend.user.domain.model.aggregates.UserDetails;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserDetails, String> {
    Optional<UserDetails> findByUserId(String userId);
}
