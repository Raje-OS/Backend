package raje.com.rajebackend.person.interfaces.rest.transform;

import raje.com.rajebackend.person.domain.model.aggregates.Actor;
import raje.com.rajebackend.person.interfaces.rest.resources.ActorResource;

public class ActorResourceFromEntityAssembler {

    public static ActorResource toResourceFromEntity(Actor actor) {
        return new ActorResource(
                actor.getId(),
                actor.getNombre(),
                actor.getDescripcion(),
                actor.getFechaNacimiento(),
                actor.getCiudadOrigen().ciudad_origen(),
                actor.getImagen()
        );
    }
}
