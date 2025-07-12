package raje.com.rajebackend.platform.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.platform.domain.model.aggregates.Platform;

import java.util.Optional;

@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {
    Optional<Platform> findByCredentialEmail(String email);

    boolean existsByCredentialEmail(String email);

}
