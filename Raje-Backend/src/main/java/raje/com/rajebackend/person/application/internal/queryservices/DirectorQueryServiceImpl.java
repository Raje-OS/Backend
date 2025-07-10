package raje.com.rajebackend.person.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.person.domain.model.aggregates.Director;
import raje.com.rajebackend.person.domain.model.queries.GetDirectorByIdQuery;

import raje.com.rajebackend.person.domain.services.DirectorQueryService;
import raje.com.rajebackend.person.infrastructure.persistence.jpa.repositories.DirectorRepository;

import java.util.Optional;
@Service
public class DirectorQueryServiceImpl implements DirectorQueryService {

    private final DirectorRepository directorRepository;

    public DirectorQueryServiceImpl(DirectorRepository directorRepository) {
        this.directorRepository = directorRepository;
    }

    @Override
    public Optional<Director> handle(GetDirectorByIdQuery query) {
        return directorRepository.findById(query.id());
    }

}
