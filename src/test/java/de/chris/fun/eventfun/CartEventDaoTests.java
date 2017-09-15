package de.chris.fun.eventfun;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.events.CartEvent;
import de.chris.fun.eventfun.events.CartEventDao;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEventDaoTests {

    @Autowired
    private CartEventDao eventDao;

    @Test
    public void storeEventForNewAggregate() {

        final String aggregateId = UUID.randomUUID().toString();
        final CartEvent cartEvent = new CartEvent(aggregateId, "some data");

        eventDao.saveEventsForAggregate(aggregateId, 0, Arrays.asList(cartEvent));

        final List<CartEvent> storedEvents = eventDao.getEventsForAggregate(aggregateId);

        for (final CartEvent event : storedEvents) {
            System.out.println("got event: " + event);
        }

    }

    @Test
    public void testAggregateVersionIncremented() {

        final String aggregateId = UUID.randomUUID().toString();

        final List<CartEvent> cartEvents = Arrays.asList(
                new CartEvent(aggregateId, ""),
                new CartEvent(aggregateId, ""),
                new CartEvent(aggregateId, ""),
                new CartEvent(aggregateId, ""),
                new CartEvent(aggregateId, ""),
                new CartEvent(aggregateId, ""));

        eventDao.saveEventsForAggregate(aggregateId, 0, cartEvents);

        for (final CartEvent e : eventDao.getEventsForAggregate(aggregateId)) {
            System.out.println("got event: " + e);
        }

        // check if the correct version was saved in the aggregate
        assertEquals(6, eventDao.getVersionForAggregate(aggregateId));

    }

}
