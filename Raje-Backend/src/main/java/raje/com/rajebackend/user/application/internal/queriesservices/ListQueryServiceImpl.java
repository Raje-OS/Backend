package raje.com.rajebackend.user.application.internal.queryservices;

import org.springframework.stereotype.Service;
import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;
import raje.com.rajebackend.user.domain.model.queries.GetListsByUserIdQuery;
import raje.com.rajebackend.user.domain.services.ListQueryService;
import raje.com.rajebackend.user.infrastructure.persistence.jpa.repositories.ListRepository;

import java.util.List;

@Service
public class ListQueryServiceImpl implements ListQueryService {

    private final ListRepository listRepository;

    public ListQueryServiceImpl(ListRepository listRepository) {
        this.listRepository = listRepository;
    }

    @Override
    public List<ListEntity> handle(GetListsByUserIdQuery query) {
        return listRepository.findByUserId(query.userId());
    }
}
