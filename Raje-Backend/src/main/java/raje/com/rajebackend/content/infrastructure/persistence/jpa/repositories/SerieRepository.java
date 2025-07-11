package raje.com.rajebackend.content.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.content.domain.model.aggregates.Serie;

@Repository
public interface SerieRepository extends JpaRepository<Serie, String> {
}
