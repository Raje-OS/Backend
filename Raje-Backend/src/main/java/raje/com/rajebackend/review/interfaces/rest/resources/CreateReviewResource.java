package raje.com.rajebackend.review.interfaces.rest.resources;

import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CreateReviewResource(


        String id,


        String userId,


        String contenidoId,

        @Min(value = 0, message = "La calificación mínima es 0")
        @Max(value = 5, message = "La calificación máxima es 5")
        int rating,


        String text,

        LocalDate createdAt

) {}
