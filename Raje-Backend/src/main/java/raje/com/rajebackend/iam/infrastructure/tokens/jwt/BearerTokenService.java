package raje.com.rajebackend.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;

/**
 * Specialized interface for RAJE's JWT token service.
 * <p>
 * Extends {@link TokenService} and adds methods for:
 * - Extracting JWT tokens from HTTP requests
 * - Generating tokens directly from {@link Authentication} objects
 * </p>
 */
public interface BearerTokenService extends TokenService {

    /**
     * Extracts the JWT token from the Authorization header in an HTTP request.
     *
     * @param request the HTTP request containing the token
     * @return the JWT token without the "Bearer " prefix, or null if not present
     */
    String getBearerTokenFrom(HttpServletRequest request);

    /**
     * Generates a JWT token from an authentication object.
     *
     * @param authentication the authentication object
     * @return the generated JWT token
     */
    String generateToken(Authentication authentication);
}
