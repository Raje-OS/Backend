package raje.com.rajebackend.shared.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * Base class for all aggregate roots that require auditing.
 * @param <T> the type of the aggregate root
 * @summary This class is an abstract class that extends AbstractAggregateRoot to provide auditing capabilities
 */

@Getter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableAbstractAggregateRoot<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> {




    public void addDomainEvent(Object event) {
        super.registerEvent(event);
    }

    public AuditableAbstractAggregateRoot() {

    }
}
