package raje.com.rajebackend.iam.infrastructure.authorization.sfs.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import raje.com.rajebackend.iam.domain.model.aggregates.User;
import raje.com.rajebackend.iam.infrastructure.authorization.sfs.model.UserDetailsImpl;
import raje.com.rajebackend.iam.infrastructure.persistence.jpa.repositories.UserRepository;

/**
 * Default implementation of {@link UserDetailsService}.
 * <p>
 * Loads user information from the database and maps it to a {@link UserDetails} instance
 * used by Spring Security for authentication and authorization.
 */
@Service(value = "defaultUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Loads the user by username from the database.
     *
     * @param username the username to search for
     * @return a {@link UserDetails} instance representing the authenticated user
     * @throws UsernameNotFoundException if no user is found
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + username));
        return UserDetailsImpl.build(user);
    }
}
