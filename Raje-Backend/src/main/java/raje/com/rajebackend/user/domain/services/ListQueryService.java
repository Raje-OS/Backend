package raje.com.rajebackend.user.domain.services;

import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;
import raje.com.rajebackend.user.domain.model.queries.GetListsByUserIdQuery;

import java.util.List;

public interface ListQueryService {
    List<ListEntity> handle(GetListsByUserIdQuery query);
}
