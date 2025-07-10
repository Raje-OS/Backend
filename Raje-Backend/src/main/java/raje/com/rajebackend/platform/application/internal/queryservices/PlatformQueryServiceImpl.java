package raje.com.rajebackend.platform.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.platform.domain.model.aggregates.Platform;
import raje.com.rajebackend.platform.domain.model.queries.*;
import raje.com.rajebackend.platform.domain.services.PlatformQueryService;
import raje.com.rajebackend.platform.infrastructure.persistence.jpa.repositories.PlatformRepository;

import java.util.List;
import java.util.Optional;

@Service
public class PlatformQueryServiceImpl implements PlatformQueryService {

    private final PlatformRepository repository;

    public PlatformQueryServiceImpl(PlatformRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Platform> handle(GetPlatformByIdQuery query) {
        return repository.findById(query.platformId());
    }

    @Override
    public List<Platform> handle(GetPlatformsByIdsQuery query) {
        return repository.findAllById(query.platformIds());
    }

    @Override
    public List<Platform> handle(GetAllPlatformsQuery query) {
        return repository.findAll();
    }
}
