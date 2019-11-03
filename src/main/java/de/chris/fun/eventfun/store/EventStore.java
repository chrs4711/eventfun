package de.chris.fun.eventfun.store;

import java.util.List;

/**
 * 
 * Interface for a event store which stores raw events. No domain typing shit
 * here.
 *
 * @author Christian Wander
 */
public interface EventStore {

    /**
     * @param data
     * @param eventType
     * @param aggregateId
     * @return The id of the aggregate the event belongs to.
     */
    String save(String data, String eventType, String aggregateId);

    /**
     * Saves an event for a previously not existing aggregate.
     * 
     * @param data
     * @param eventType
     * @return the id of the newly created aggregate
     */
    String save(String data, String eventType);

    /**
     * Retrieves all events belonging to a specific aggregate.
     * 
     * @param aggregateId
     * @return A list with all events for the given aggregate
     */
    List<Event> getRawEvents(String aggregateId);

    /**
     * Checks if an aggregate exists for the given aggregate ID
     * 
     * @param aggregateId
     * @return true if the aggregate exists.
     */
    boolean aggregateExists(String aggregateId);

}