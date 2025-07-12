package raje.com.rajebackend.user.domain.model.commands;

import java.util.List;

public record CreateUserDetailsCommand(
        String id,
        String userId,
        List<String> favorites,
        List<String> viewed
) {}
