package raje.com.rajebackend.user.interfaces.rest.transform;

import raje.com.rajebackend.user.domain.model.aggregates.ListEntity;
import raje.com.rajebackend.user.interfaces.rest.resources.ListResource;

public class ListResourceFromEntityAssembler {

    public static ListResource toResourceFromEntity(ListEntity list) {
        return new ListResource(
                list.getId(),
                list.getUserId(),
                list.getName(),
                list.getDescription(),
                list.getList_content()
        );
    }
}
