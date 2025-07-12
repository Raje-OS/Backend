package raje.com.rajebackend.iam.infrastructure.authorization.sfs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import raje.com.rajebackend.iam.domain.model.aggregates.User;

import java.util.Collection;
import java.util.List;

/**
 * Custom implementation of {@link UserDetails} for Spring Security.
 * <p>
 * Wraps a domain {@link User} entity and exposes required authentication details.
 */
@Getter
@EqualsAndHashCode
public class UserDetailsImpl implements UserDetails {

    private final String username;

    @JsonIgnore
    private final String password;

    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;

    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        this.username = username;
        this.password = password;
        this.authorities = authorities;
        this.accountNonExpired = true;
        this.accountNonLocked = true;
        this.credentialsNonExpired = true;
        this.enabled = true;
    }

    /**
     * Builds a {@link UserDetailsImpl} object from a {@link User} entity.
     *
     * @param user the domain user
     * @return a UserDetailsImpl instance for authentication
     */
    public static UserDetailsImpl build(User user) {
        return new UserDetailsImpl(
                user.getUserName(),
                user.getPassword(),
                List.of() // No roles/authorities assigned for now
        );
    }
}
