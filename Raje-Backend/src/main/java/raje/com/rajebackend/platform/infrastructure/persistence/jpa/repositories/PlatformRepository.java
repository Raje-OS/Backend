package raje.com.rajebackend.platform.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.platform.domain.model.aggregates.Platform;

import java.util.Optional;

/**
 * JPA repository for accessing and managing Platform entities in the database.
 */
@Repository
public interface PlatformRepository extends JpaRepository<Platform, String> {

    /**
     * Retrieves a platform by its credential email.
     *
     * @param email the email associated with the platform's credentials
     * @return an Optional containing the Platform if found, otherwise empty
     */
    Optional<Platform> findByCredentialEmail(String email);

    /**
     * Checks if a platform exists by its credential email.
     *
     * @param email the email to check for existence
     * @return true if a platform with the given email exists, false otherwise
     */
    boolean existsByCredentialEmail(String email);
}
