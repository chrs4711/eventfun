package de.chris.fun.eventfun.store.serialize;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.Event;

/**
 * @author Christian Wander
 *
 */
public class JsonSerializ0r implements DomainEventSerializ0r {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String serialize(DomainEvent event) {

        try {
            return objectMapper.writeValueAsString(event);
        } catch (final JsonProcessingException e) {
            throw new DomainEventSerializationException(e);
        }
    }

    @Override
    public DomainEvent deserialize(Event event, List<Class<? extends DomainEvent>> domainEventClasses) {

        for (final Class<? extends DomainEvent> c : domainEventClasses) {
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
