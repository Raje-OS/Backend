package raje.com.rajebackend.user.interfaces.rest.resources;

import java.util.List;

public record UserDetailsResource(
        String id,
        String userId,
        List<String> favorites,
        List<String> viewed
) {}
