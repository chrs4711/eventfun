package de.chris.fun.eventfun.store.memory;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.chris.fun.eventfun.store.Aggregate;
import de.chris.fun.eventfun.store.Event;
import de.chris.fun.eventfun.store.EventStore;
import de.chris.fun.eventfun.store.NoSuchAggregateException;

/**
 * @author Christian Wander
 *
 */
public class MemoryEventStore implements EventStore {

    private final Map<String, Event> eventMap = new HashMap<>();
    private final Map<String, Aggregate> aggMap = new HashMap<>();

    private static final Logger logger = LoggerFactory.getLogger(MemoryEventStore.class);

    @Override
    public String save(String data, String eventType) {

        final Aggregate a = new Aggregate();
        final String aggregateId = UUID.randomUUID().toString();
        a.setAggregateId(aggregateId);
        a.setVersion(0);

        logger.debug("created new aggregate with id {}", a);
        aggMap.put(aggregateId, a);

        return save(data, eventType, aggregateId);
    }

    @Override
    public String save(String data, String eventType, String aggregateId) {

        if (!aggMap.containsKey(aggregateId))
            throw new NoSuchAggregateException(String.format("aggregate with id %s not found", aggregateId));

        final Aggregate agg = aggMap.get(aggregateId);

        final long newVersion = agg.getVersion() + 1;

        final Event e = new Event();
        e.setAggregateId(aggregateId);
        e.setId(UUID.randomUUID().toString());
        e.setType(eventType);
        e.setVersion(newVersion);
        e.setData(data);

        agg.setVersion(newVersion);

        // magic happens here
        eventMap.put(e.getId(), e);

        logger.debug("updating aggregate: {}", agg);
        logger.debug("saving event: {}", e);

        return aggregateId;
    }

    @Override
    public List<Event> getRawEvents(String aggregateId) {

        final List<Event> events = eventMap.values().stream()
                .filter(e -> e.getAggregateId().equals(aggregateId))
                .collect(Collectors.toList());

        Collections.sort(events, (e1, e2) -> {
            return e1.getVersion() >= e2.getVersion() ? 1 : -1; // ASC
        });

        return events;
    }

    @Override
    public boolean aggregateExists(String aggregateId) {
        return aggMap.containsKey(aggregateId);
    }
}
