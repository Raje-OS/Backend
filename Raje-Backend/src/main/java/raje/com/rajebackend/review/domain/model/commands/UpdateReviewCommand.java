package raje.com.rajebackend.review.domain.model.commands;

public record UpdateReviewCommand(
        int rating,
        String text
) {}
