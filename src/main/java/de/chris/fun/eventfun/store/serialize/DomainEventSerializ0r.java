package de.chris.fun.eventfun.store.serialize;

import java.util.List;

import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.Event;

/**
 * @author Christian Wander
 *
 */
public interface DomainEventSerializ0r<T> {

    /**
     * Inform the serializer of the existing domain events. TODO: This is awkward.
     * Maybe use spring to scan for valid types
     * 
     * @param knownDomainEventTypes
     */
    public void setKnownDomainEventTypes(List<Class<? extends DomainEvent<T>>> knownDomainEventTypes);

    /**
     * Returns the types of domain events the serializer knows about.
     * 
     * @return
     */
    public List<Class<? extends DomainEvent<T>>> getKnownDomainEventTypes();

    public String serialize(DomainEvent<T> event);

    public DomainEvent<T> deserialize(Event event);

}
