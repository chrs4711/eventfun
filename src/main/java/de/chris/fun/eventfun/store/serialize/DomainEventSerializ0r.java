package de.chris.fun.eventfun.store.serialize;

import java.util.List;

import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.Event;

/**
 * @author Christian Wander
 *
 */
public interface DomainEventSerializ0r {

    /**
     * Inform the serializer of the existing domain events. TODO: This is awkward.
     * Maybe use spring to scan for valid types
     * 
     * @param knownDomainEventTypes
     */
    public void setKnownDomainEventTypes(List<Class<? extends DomainEvent>> knownDomainEventTypes);

    /**
     * Returns the types of domain events the serializer knows about.
     * 
     * @return
     */
    public List<Class<? extends DomainEvent>> getKnownDomainEventTypes();

    public String serialize(DomainEvent event);

    public DomainEvent deserialize(Event event);

}
