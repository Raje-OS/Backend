package raje.com.rajebackend.platform.domain.services;

import raje.com.rajebackend.platform.domain.model.aggregates.Platform;
import raje.com.rajebackend.platform.domain.model.queries.*;

import java.util.List;
import java.util.Optional;

public interface PlatformQueryService {
    Optional<Platform> handle(GetPlatformByIdQuery query);
    List<Platform> handle(GetPlatformsByIdsQuery query);
    List<Platform> handle(GetAllPlatformsQuery query);
}
