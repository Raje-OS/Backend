package raje.com.rajebackend.platform.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.platform.domain.model.aggregates.Platform;
import raje.com.rajebackend.platform.domain.model.queries.*;
import raje.com.rajebackend.platform.domain.services.PlatformQueryService;
import raje.com.rajebackend.platform.infrastructure.persistence.jpa.repositories.PlatformRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for handling queries related to platforms.
 */
@Service
public class PlatformQueryServiceImpl implements PlatformQueryService {

    private final PlatformRepository repository;

    /**
     * Constructs a new PlatformQueryServiceImpl with the specified repository.
     *
     * @param repository the repository used to query platform data
     */
    public PlatformQueryServiceImpl(PlatformRepository repository) {
        this.repository = repository;
    }

    /**
     * Retrieves a platform by its ID.
     *
     * @param query the query containing the platform ID
     * @return an Optional containing the Platform if found, otherwise empty
     */
    @Override
    public Optional<Platform> handle(GetPlatformByIdQuery query) {
        return repository.findById(query.platformId());
    }

    /**
     * Retrieves a list of platforms by their IDs.
     *
     * @param query the query containing a list of platform IDs
     * @return a list of Platform objects matching the given IDs
     */
    @Override
    public List<Platform> handle(GetPlatformsByIdsQuery query) {
        return repository.findAllById(query.platformIds());
    }

    /**
     * Retrieves all platforms.
     *
     * @param query the query to retrieve all platforms (no parameters)
     * @return a list of all Platform objects in the repository
     */
    @Override
    public List<Platform> handle(GetAllPlatformsQuery query) {
        return repository.findAll();
    }
}
