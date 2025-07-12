package raje.com.rajebackend.review.interfaces.rest.resources;

import java.time.LocalDateTime;

public record ReviewResource(
        String id,
        String userId,
        String contenidoId,
        int rating,
        String text
) {}
