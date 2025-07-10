package raje.com.rajebackend.platform.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import raje.com.rajebackend.platform.domain.model.valueobjects.Credential;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Entity
public class Platform extends AuditableAbstractAggregateRoot<Platform> {

    private String Platformid;   // ID manual como "PL001"

    private String nombre;
    private String descripcion;
    private String imagen;

    @Embedded
    private Credential credential;

    public Platform() {
        this.Platformid = "";
        this.nombre = "";
        this.descripcion = "";
        this.imagen = "";
        this.credential = new Credential("", "");
    }

    public Platform(String Platformid, String nombre, String descripcion, String email, String password, String imagen) {
        this.Platformid = Platformid;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.credential = new Credential(email, password);
        this.imagen = imagen;
    }
}
