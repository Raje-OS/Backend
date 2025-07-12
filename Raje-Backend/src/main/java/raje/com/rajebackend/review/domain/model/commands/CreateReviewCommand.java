package raje.com.rajebackend.review.domain.model.commands;

import java.time.LocalDateTime;

public record CreateReviewCommand(
        String id,
        String userId,
        String contenidoId,
        int rating, // de 0 a 5
        String text
) {}
