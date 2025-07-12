package raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;

import java.util.List;

@Repository
public interface ListRepository extends JpaRepository<ListEntity, String> {
    List<ListEntity> findByUserId(String userId);
}
