package raje.com.rajebackend.user.interfaces.rest.resources;

import java.util.List;

public record ListResource(
        String id,
        String userId,
        String name,
        String description,
        List<String> list_content
) {}
