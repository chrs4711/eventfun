/**
 * This code is property of:
 *
 * Hamm-Reno Group GmbH & Co. KG
 * Am Tie 7
 * D-49086 Osnabrück
 * Telefon: +49 (0)541 / 9584-0
 * Telefax: +49 (0)541 / 9584-9221
 *
 * (c) 2017 - 2017 all rights reserved.
 * 
 */
package de.chris.fun.eventfun.store;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.chris.fun.eventfun.store.serialize.DomainEventSerializ0r;

/**
 * @author Christian Wander
 *
 */
@Component
public class EventStore {

    @Autowired
    private EventRepository eventRepo;

    @Autowired
    private AggregateRepository aggRepo;

    @Autowired
    private DomainEventSerializ0r serializ0r;

    private static final Logger logger = LoggerFactory.getLogger(EventStore.class);

    public String save(DomainEvent event) {
        final Aggregate agg = makeNewAggregate();
        aggRepo.save(agg);

        return save(event, agg.getAggregateId());
    }

    public String save(DomainEvent event, String aggregateId) {

        Aggregate agg = aggRepo.findOne(aggregateId);

        if (agg == null)
            agg = makeNewAggregate();

        final long newVersion = agg.getVersion() + 1;

        final Event e = new Event();
        e.setId(UUID.randomUUID().toString());
        e.setAggregateId(agg.getAggregateId());
        e.setType(event.getClass().getSimpleName());
        e.setVersion(newVersion);
        e.setData(serializ0r.serialize(event));

        agg.setVersion(newVersion);
        logger.debug("updating aggregate: {}", agg);
        agg = aggRepo.save(agg);

        logger.debug("saving event: {}", e);
        eventRepo.save(e);

        return agg.getAggregateId();
    }

    public List<Event> retrieveForAggregate(String aggregateId) {
        return eventRepo.findByAggregateIdOrderByVersion(aggregateId);
    }

    private Aggregate makeNewAggregate() {
        final Aggregate agg = new Aggregate();
        agg.setAggregateId(UUID.randomUUID().toString());
        agg.setVersion(0);
        return agg;
    }

}
