package raje.com.rajebackend.user.domain.model.aggregates;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import raje.com.rajebackend.user.domain.model.commands.CreateListCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateListCommand;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class ListEntity extends AuditableAbstractAggregateRoot<ListEntity> {

    @Id
    private String id;

    private String userId;

    private String name;

    private String description;

    @ElementCollection
    private List<String> list_content = new ArrayList<>();

    public ListEntity() {
        this.id = "";
        this.userId = "";
        this.name = "";
        this.description = "";
    }

    public ListEntity(CreateListCommand command) {
        this.id = command.id();
        this.userId = command.userId();
        this.name = command.name();
        this.description = command.description();
        this.list_content = new ArrayList<>(command.list_content());
    }

    public void update(UpdateListCommand command) {
        this.name = command.name();
        this.description = command.description();
        this.list_content = new ArrayList<>(command.list_content());
    }
}
