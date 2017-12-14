package de.chris.fun.eventfun.store;

import java.util.List;

/**
 * @author Christian Wander
 *
 */
public interface EventStore {

    /**
     * Save a DomainEvent to the event store. A new aggregate will be created for
     * the event.
     * 
     * @param event
     *            the DomainEvent
     * @return the aggregate id
     */
    String save(DomainEvent event);

    /**
     * Save a DomainEvent for an aggregate to the event store.
     * 
     * @param event
     *            The DomainEvent
     * @param aggregateId
     *            Id of the aggregate the event belongs to.
     * @return the Id of the aggregate.
     */
    String save(DomainEvent event, String aggregateId);

    /**
     * Retrieves all events belonging to a specific aggregate.
     * 
     * @param aggregateId
     * @return
     */
    List<Event> retrieveForAggregate(String aggregateId);

}