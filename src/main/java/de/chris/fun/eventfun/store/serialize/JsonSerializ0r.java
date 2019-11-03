package de.chris.fun.eventfun.store.serialize;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.Event;

/**
 * @author Christian Wander
 * @param <T>
 *
 */
public class JsonSerializ0r<T> implements DomainEventSerializ0r<T> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    private List<Class<? extends DomainEvent<T>>> knownDomainEventTypes;

    @Override
    public List<Class<? extends DomainEvent<T>>> getKnownDomainEventTypes() {
        return knownDomainEventTypes;
    }

    @Override
    public void setKnownDomainEventTypes(List<Class<? extends DomainEvent<T>>> knownDomainEventTypes) {
        this.knownDomainEventTypes = knownDomainEventTypes;
    }

    @Override
    public String serialize(DomainEvent<T> event) {

        try {
            return objectMapper.writeValueAsString(event);
        } catch (final JsonProcessingException e) {
            throw new DomainEventSerializationException(e);
        }
    }

    @Override
    public DomainEvent<T> deserialize(Event event) {

        for (final Class<? extends DomainEvent<T>> c : knownDomainEventTypes) {
            if (c.getSimpleName().equals(event.getType())) {
                try {
                    return objectMapper.readValue(event.getData(), c);
                } catch (final IOException e) {
                    throw new DomainEventSerializationException(e);
                }
            }
        }

        throw new DomainEventSerializationException("unknown event type: " + event.getType());
    }

}
