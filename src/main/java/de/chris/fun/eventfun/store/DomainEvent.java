package de.chris.fun.eventfun.store;

/**
 * Marker interface for domain events. The event store accepts objects
 * implementing this interface and tries to serialize them to JSON (other
 * formats might be supported in future :-).
 * 
 * @author Christian Wander
 *
 */
public interface DomainEvent {

    public <T> T apply(T domainObject);

}
