package de.chris.fun.eventfun.events;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Christian Wander
 *
 */
@Component
public class CartEventDao {

    private final static Logger logger = LoggerFactory.getLogger(CartEventDao.class);

    @Autowired
    private CartAggregateRepository aggRepo;

    @Autowired
    private CartEventRepository eventRepo;

    public List<CartEvent> getEventsForAggregate(String aggregateId) {

        return eventRepo.findByAggregateIdOrderByEventTimestampAsc(aggregateId);
    }

    public void saveEventsForAggregate(String aggregateId, int expectedVersion, List<CartEvent> events) {

        // ---------- XXX imaginary transaction boundary ------------------------

        CartAggregate agg = aggRepo.findOne(aggregateId);
        if (agg == null) {
            agg = aggRepo.save(new CartAggregate(aggregateId, 0));
        }

        if (agg.getVersion() != expectedVersion) {
            logger.error("version makes no sense");
            return; // error XXX
        }

        int eventVersion = agg.getVersion();

        // store events ... we assume that they're in order :-)
        for (final CartEvent event : events) {
            event.setVersion(++eventVersion);
            eventRepo.save(event);
        }

        // ------------- imaginary transaction boundary ------------------------
    }
}
