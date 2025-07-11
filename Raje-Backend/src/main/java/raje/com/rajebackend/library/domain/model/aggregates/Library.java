package raje.com.rajebackend.library.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import raje.com.rajebackend.library.domain.model.valueobjects.Location;
import raje.com.rajebackend.platform.domain.model.valueobjects.Credential;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class Library extends AuditableAbstractAggregateRoot<Library> {

    @Id
    private String id;

    private String nombre;
    private String descripcion;
    private String imagen;

    @Embedded
    private Credential credential;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Location> ubicaciones = new ArrayList<>();

    protected Library() {
        this.id = "";
        this.nombre = "";
        this.descripcion = "";
        this.imagen = "";
        this.credential = new Credential("", "");
    }

    public Library(String id, String nombre, String descripcion, String imagen, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.credential = new Credential(email, password);
    }

    public void addUbicacion(Location location) {
        this.ubicaciones.add(location);
    }

    public void removeUbicacionPorDireccion(String direccion) {
        this.ubicaciones.removeIf(loc -> loc.getDireccion().equalsIgnoreCase(direccion));
    }
}
