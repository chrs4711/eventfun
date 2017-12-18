package de.chris.fun.eventfun.store.serialize;

import java.util.List;

import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.Event;

/**
 * @author Christian Wander
 *
 */
public interface DomainEventSerializ0r {

    public String serialize(DomainEvent event);

    DomainEvent deserialize(Event event, List<Class<? extends DomainEvent>> domainEventClasses);

}
