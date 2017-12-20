package de.chris.fun.eventfun.store;

import java.util.List;

import de.chris.fun.eventfun.store.serialize.DomainEventSerializ0r;

/**
 * @author Christian Wander
 *
 */
public interface EventStore {

    /**
     * Configure how to de-/serialize domain events
     * 
     * @param s
     */
    void setSerializ0r(DomainEventSerializ0r s);

    /**
     * Save a DomainEvent to the event store. A new aggregate will be created for
     * the event.
     * 
     * @param event
     *            the DomainEvent
     * @return the aggregate id
     */
    String save(DomainEvent<?> event);

    /**
     * Save a DomainEvent for an aggregate to the event store.
     * 
     * @param event
     *            The DomainEvent
     * @param aggregateId
     *            Id of the aggregate the event belongs to.
     * @return the Id of the aggregate.
     */
    String save(DomainEvent<?> event, String aggregateId);

    /**
     * Retrieves all events belonging to a specific aggregate.
     * 
     * @param aggregateId
     * @return
     */
    List<Event> getRawEvents(String aggregateId);

    /**
     * Retrieves all domain events belonging to a specific aggregate.
     * 
     * @param aggregateId
     * @return
     */
    List<DomainEvent<?>> get(String aggregateId);

    /**
     * Checks if an aggregate exists for the given aggregate ID
     * 
     * @param aggregateId
     * @return true if the aggregate exists.
     */
    boolean aggregateExists(String aggregateId);

}