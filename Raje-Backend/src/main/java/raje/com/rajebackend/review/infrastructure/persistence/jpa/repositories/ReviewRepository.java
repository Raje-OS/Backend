package raje.com.rajebackend.review.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import raje.com.rajebackend.review.domain.model.aggregates.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {

    List<Review> findByUserId(String userId);
    List<Review> findByContenidoId(String contenidoId);
}
