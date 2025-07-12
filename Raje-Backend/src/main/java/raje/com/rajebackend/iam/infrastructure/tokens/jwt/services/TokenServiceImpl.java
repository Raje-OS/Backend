package raje.com.rajebackend.iam.infrastructure.tokens.jwt.services;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import raje.com.rajebackend.iam.infrastructure.tokens.jwt.BearerTokenService;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.function.Function;

/**
 * Implementation of the JWT token service for RAJE.
 * Responsible for generating, validating, and extracting data from JWT tokens.
 */
@Service
public class TokenServiceImpl implements BearerTokenService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TokenServiceImpl.class);
    private static final String AUTH_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";
    private static final int TOKEN_BEGIN_INDEX = 7;

    @Value("${authorization.jwt.secret}")
    private String secret;

    @Value("${authorization.jwt.expiration.days}")
    private int expirationDays;


    /**
     * Generates a JWT token using the authenticated user's details.
     * @param authentication the Spring Security Authentication object
     * @return a signed JWT token
     */
    @Override
    public String generateToken(Authentication authentication) {
        return buildToken(authentication.getName());
    }

    /**
     * Generates a JWT token from a given username.
     * @param username the user’s username
     * @return a signed JWT token
     */
    @Override
    public String generateToken(String username) {
        return buildToken(username);
    }

    private String buildToken(String username) {
        Date issuedAt = new Date();
        Date expiration = DateUtils.addDays(issuedAt, expirationDays);
        SecretKey key = getSigningKey();

        return Jwts.builder()
                .subject(username)
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(key)
                .compact();
    }

    @Override
    public String getUsernameFromToken(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token) {
        try {
            Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token);
            LOGGER.info("Token válido.");
            return true;
        } catch (SignatureException e) {
            LOGGER.error("Firma inválida: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            LOGGER.error("Token mal formado: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            LOGGER.error("Token expirado: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            LOGGER.error("Token no soportado: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            LOGGER.error("Claims vacíos: {}", e.getMessage());
        }
        return false;
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getSigningKey()).build().parseSignedClaims(token).getPayload();
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Bearer token helpers

    private boolean isTokenPresent(String authHeader) {
        return StringUtils.hasText(authHeader);
    }

    private boolean isBearerToken(String authHeader) {
        return authHeader.startsWith(BEARER_PREFIX);
    }

    private String extractToken(String authHeader) {
        return authHeader.substring(TOKEN_BEGIN_INDEX);
    }

    private String getAuthorizationHeader(HttpServletRequest request) {
        return request.getHeader(AUTH_HEADER);
    }

    @Override
    public String getBearerTokenFrom(HttpServletRequest request) {
        String header = getAuthorizationHeader(request);
        if (isTokenPresent(header) && isBearerToken(header)) return extractToken(header);
        return null;
    }
}
