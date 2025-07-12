package raje.com.rajebackend.review.interfaces.rest.resources;

import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

public record CreateReviewResource(

        @NotBlank(message = "El ID es obligatorio")
        String id,

        @NotBlank(message = "El ID del usuario es obligatorio")
        String userId,

        @NotBlank(message = "El ID del contenido es obligatorio")
        String contenidoId,

        @Min(value = 0, message = "La calificación mínima es 0")
        @Max(value = 5, message = "La calificación máxima es 5")
        int rating,

        @Size(max = 4000, message = "El texto de la reseña no puede exceder los 4000 caracteres")
        String text

) {}
