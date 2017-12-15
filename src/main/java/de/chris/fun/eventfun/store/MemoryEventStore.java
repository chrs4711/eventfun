package de.chris.fun.eventfun.store;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.chris.fun.eventfun.store.serialize.DomainEventSerializ0r;
import de.chris.fun.eventfun.store.serialize.JsonSerializ0r;

/**
 * @author Christian Wander
 *
 */
public class MemoryEventStore implements EventStore {

    private final Map<String, Event> eventMap = new HashMap<>();
    private final Map<String, Aggregate> aggMap = new HashMap<>();

    private final DomainEventSerializ0r s = new JsonSerializ0r();

    private static final Logger logger = LoggerFactory.getLogger(MemoryEventStore.class);

    @Override
    public String save(DomainEvent event) {

        final Aggregate a = new Aggregate();
        final String aggregateId = UUID.randomUUID().toString();
        a.setAggregateId(aggregateId);
        a.setVersion(0);

        System.out.println("new aggregate: " + a);
        aggMap.put(aggregateId, a);

        save(event, aggregateId);

        return aggregateId;
    }

    @Override
    public String save(DomainEvent event, String aggregateId) {

        if (!aggMap.containsKey(aggregateId))
            throw new NoSuchAggregateException(String.format("aggregate with id %s not found", aggregateId));

        final Aggregate agg = aggMap.get(aggregateId);

        final long newVersion = agg.getVersion() + 1;

        final Event e = new Event();
        e.setAggregateId(aggregateId);
        e.setId(UUID.randomUUID().toString());
        e.setType(event.getClass().getSimpleName());
        e.setVersion(newVersion);
        e.setData(s.serialize(event));

        agg.setVersion(newVersion);
        logger.debug("updating aggregate: {}", agg);
        logger.debug("saving event: {}", e);

        eventMap.put(e.getId(), e);

        return aggregateId;
    }

    @Override
    public List<Event> retrieveForAggregate(String aggregateId) {

        final List<Event> events = eventMap.values().stream()
                .filter(e -> e.getAggregateId().equals(aggregateId))
                .collect(Collectors.toList());

        Collections.sort(events, (e1, e2) -> {
            return e1.getVersion() >= e2.getVersion() ? 1 : -1;
        });

        return events;
    }

}
