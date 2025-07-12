package raje.com.rajebackend.iam.infrastructure.tokens.jwt;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import raje.com.rajebackend.iam.application.internal.outboundservices.tokens.TokenService;

/**
 * Interfaz especializada para el servicio JWT de RAJE.
 * <p>
 * Extiende {@link TokenService} e incluye métodos adicionales para:
 * - Extraer el token JWT desde un request HTTP
 * - Generar un token directamente desde un {@link Authentication}
 * </p>
 */
public interface BearerTokenService extends TokenService {

    /**
     * Extrae el token JWT del encabezado Authorization en un request HTTP.
     *
     * @param request el request HTTP que contiene el token
     * @return el token JWT sin el prefijo "Bearer ", o null si no está presente
     */
    String getBearerTokenFrom(HttpServletRequest request);

    /**
     * Genera un token JWT a partir de un objeto de autenticación.
     *
     * @param authentication el objeto de autenticación
     * @return el token JWT generado
     */
    String generateToken(Authentication authentication);
}
