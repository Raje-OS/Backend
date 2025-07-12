package raje.com.rajebackend.user.domain.model.commands;

import java.util.List;

public record UpdateUserDetailsCommand(
        List<String> favorites,
        List<String> viewed
) {}
