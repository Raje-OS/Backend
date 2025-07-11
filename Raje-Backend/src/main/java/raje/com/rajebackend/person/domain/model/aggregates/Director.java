package raje.com.rajebackend.person.domain.model.aggregates;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import raje.com.rajebackend.person.domain.model.commands.CreateDirectorCommand;
import raje.com.rajebackend.person.domain.model.valueobjects.City;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.time.LocalDate;

@Getter
@Entity
public class Director extends AuditableAbstractAggregateRoot<Director> {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    private LocalDate fechaNacimiento;

    @Embedded
    private City ciudadOrigen;

    private String imagen;

    public Director() {
        this.nombre = "";
        this.descripcion = "";
        this.fechaNacimiento = LocalDate.now();
        this.ciudadOrigen = new City("");
        this.imagen = "";
    }

    public Director(CreateDirectorCommand command) {
        this.id = command.id();
        this.nombre = command.nombre();
        this.descripcion = command.descripcion();
        this.fechaNacimiento = command.fechaNacimiento();
        this.ciudadOrigen = new City(command.ciudad_origen());
        this.imagen = command.imagen();
    }

    public Director updateInfo(String descripcion, String imagen) {
        this.descripcion = descripcion;
        this.imagen = imagen;
        return this;
    }
}
