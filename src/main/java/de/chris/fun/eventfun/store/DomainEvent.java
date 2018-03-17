package de.chris.fun.eventfun.store;

/**
 * Marks a class as a domain event.
 * 
 * @author Christian Wander
 *
 */
public interface DomainEvent<T> {

    /**
     * Applies this event to a specific domain object.
     * 
     * @param object
     *            The domain object
     * @return The domain object after the event was applied to it.
     */
    public T apply(T object);

}
