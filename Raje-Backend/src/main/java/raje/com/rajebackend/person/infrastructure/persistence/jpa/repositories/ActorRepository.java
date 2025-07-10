package raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.person.domain.model.aggregates.Actor;

@Repository
public interface ActorRepository extends JpaRepository<Actor, String> {
}
