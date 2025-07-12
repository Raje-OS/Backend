package raje.com.rajebackend.iam.infrastructure.authorization.sfs.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.web.cors.CorsConfiguration;
import raje.com.rajebackend.iam.infrastructure.hashing.bcrypt.BCryptHashingService;

import java.util.List;

@Configuration
public class WebSecurityConfiguration {

    private final BCryptHashingService hashingService;

    // Constructor injecting the BCrypt hashing service used for password encoding
    public WebSecurityConfiguration(BCryptHashingService hashingService) {
        this.hashingService = hashingService;
    }

    /**
     * Bean for obtaining the AuthenticationManager from the AuthenticationConfiguration.
     * Required for manual authentication processes.
     */
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    /**
     * Bean for password encoding. Uses the custom BCryptHashingService implementation.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return hashingService;
    }

    /**
     * Configures the security filter chain:
     * - Enables CORS for all origins, methods, and headers
     * - Disables CSRF protection
     * - Disables session creation (stateless)
     * - Permits all HTTP requests (no restrictions set here)
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.cors(cors -> cors.configurationSource(req -> {
                    var config = new CorsConfiguration();
                    config.setAllowedOrigins(List.of("*")); // Allow all origins
                    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Allow common HTTP methods
                    config.setAllowedHeaders(List.of("*")); // Allow all headers
                    return config;
                }))
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection
                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Stateless sessions (no HTTP sessions)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll() // Allow all requests (can be restricted in the future)
                );

        return http.build();
    }
}
