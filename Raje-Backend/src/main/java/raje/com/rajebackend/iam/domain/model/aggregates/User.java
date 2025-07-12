package raje.com.rajebackend.iam.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import raje.com.rajebackend.content.domain.model.valueobjects.PlatformId;
import raje.com.rajebackend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Id
    private String id;

    private String firstName;

    private String lastName;

    private String userName;

    private String email;

    private String password;

    @ElementCollection
    private List<PlatformId> platforms = new ArrayList<>();

    private String images;

    public User(String id, String firstName, String lastName, String userName,
                String email, String password, List<PlatformId> platforms, String images) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.platforms = platforms != null ? platforms : new ArrayList<>();
        this.images = images != null ? images : "https://i.pinimg.com/236x/09/02/86/090286be7ffa5bc199ad0bb34af40d68.jpg";
    }
}
