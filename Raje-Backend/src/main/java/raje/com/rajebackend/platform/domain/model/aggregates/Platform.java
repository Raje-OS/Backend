package raje.com.rajebackend.platform.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import raje.com.rajebackend.platform.domain.model.valueobjects.Credential;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

@Getter
@Setter
@Entity
public class Platform extends AuditableAbstractAggregateRoot<Platform> {

    @Id
    private String id;   // ID manual como "PL001"

    private String nombre;
    private String descripcion;
    private String imagen;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "email", column = @Column(name = "email")),
            @AttributeOverride(name = "password", column = @Column(name = "password"))
    })
    private Credential credential;


    public Platform() {
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
        this.imagen = "";
        this.credential = new Credential("", "");
    }

    public Platform(String id, String nombre, String descripcion, String email, String password, String imagen) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.credential = new Credential(email, password);
        this.imagen = imagen;
    }
}
