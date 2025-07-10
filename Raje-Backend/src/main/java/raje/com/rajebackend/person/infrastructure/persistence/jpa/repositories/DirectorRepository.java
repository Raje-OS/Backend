package raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.person.domain.model.aggregates.Director;

@Repository
public interface DirectorRepository extends JpaRepository<Director, String> {
}
