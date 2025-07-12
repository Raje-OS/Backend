package raje.com.rajebackend.iam.infrastructure.hashing.bcrypt.services;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import raje.com.rajebackend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;

/**
 * Implementation of {@link BCryptHashingService}.
 * <p>
 * Provides password hashing and verification using the BCrypt algorithm.
 */
@Service
public class HashingServiceImpl implements BCryptHashingService {

    private final BCryptPasswordEncoder passwordEncoder;

    public HashingServiceImpl() {
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    /**
     * Hash the given raw password using BCrypt.
     *
     * @param rawPassword the plain password to hash
     * @return the hashed password
     */
    @Override
    public String encode(CharSequence rawPassword) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * Verify that the raw password matches the hashed password.
     *
     * @param rawPassword the plain password
     * @param encodedPassword the hashed password to compare against
     * @return true if the password matches, false otherwise
     */
    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
}
