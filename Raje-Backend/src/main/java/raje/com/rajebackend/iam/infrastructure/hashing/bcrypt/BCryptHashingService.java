package raje.com.rajebackend.iam.infrastructure.hashing.bcrypt;

import org.springframework.security.crypto.password.PasswordEncoder;
import raje.com.rajebackend.iam.application.internal.outboundservices.hashing.HashingService;

/**
 * Marker interface for the BCrypt hashing service.
 * <p>
 * Combines the custom {@link HashingService} abstraction with Spring's {@link PasswordEncoder}
 * to ensure compatibility with both internal logic and security configuration.
 * </p>
 */
public interface BCryptHashingService extends HashingService, PasswordEncoder {
}
