package raje.com.rajebackend.user.domain.model.aggregates;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import raje.com.rajebackend.user.domain.model.commands.CreateUserDetailsCommand;
import raje.com.rajebackend.user.domain.model.commands.UpdateUserDetailsCommand;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
public class UserDetails extends AuditableAbstractAggregateRoot<UserDetails> {

    @Id
    private String id;

    private String userId;

    @ElementCollection
    private List<String> favorites = new ArrayList<>();

    @ElementCollection
    private List<String> viewed = new ArrayList<>();

    protected UserDetails() {
        this.id = "";
        this.userId = "";
    }

    public UserDetails(CreateUserDetailsCommand command) {
        this.id = command.id();
        this.userId = command.userId();
        this.favorites = new ArrayList<>(command.favorites());
        this.viewed = new ArrayList<>(command.viewed());
    }

    public void update(UpdateUserDetailsCommand command) {
        this.favorites = new ArrayList<>(command.favorites());
        this.viewed = new ArrayList<>(command.viewed());
    }

    public UserDetails updateFrom(UserDetails updated) {
        this.favorites = new ArrayList<>(updated.getFavorites());
        this.viewed = new ArrayList<>(updated.getViewed());
        return this;
    }

}
