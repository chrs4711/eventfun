package de.chris.fun.eventfun.store;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.store.serialize.DomainEventSerializ0r;

/**
 * Saves and retrieves DomainEvents. This class is parameterized with the type
 * of the entity for which the events are managed.
 * 
 * @author Christian Wander
 *
 * @param <T>
 *            The type of the domain entity
 */
public class EventRepository<T> {

    @Autowired
    private EventStore eventStore;

    @Autowired
    private DomainEventSerializ0r<T> serializer;

    public void setSerializer(DomainEventSerializ0r<T> serializer) {
        this.serializer = serializer;
    }

    public void setEventStore(EventStore es) {
        this.eventStore = es;
    }

    public List<DomainEvent<T>> get(String aggregateId) {

        final List<Event> rawEvents = eventStore.getRawEvents(aggregateId);

        return rawEvents.stream()
                .map(e -> serializer.deserialize(e))
                .collect(Collectors.toList());
    }

    public String save(DomainEvent<T> domainEvent) {

        final String data = serializer.serialize(domainEvent);
        final String eventType = domainEvent.getClass().getSimpleName();

        return eventStore.save(data, eventType);
    }

    public String save(DomainEvent<T> domainEvent, String aggregateId) {

        final String data = serializer.serialize(domainEvent);
        final String eventType = domainEvent.getClass().getSimpleName();

        return eventStore.save(data, eventType, aggregateId);
    }

    public boolean aggregateExists(String aggregateId) {
        return eventStore.aggregateExists(aggregateId);
    }
}