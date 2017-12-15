package de.chris.fun.eventfun.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domainevents.CartCreatedEvent;
import de.chris.fun.eventfun.domainevents.ItemAddedEvent;
import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.store.memory.MemoryEventStore;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
public class EventStoreTest {

    private final EventStore eventStore = new MemoryEventStore();

    @Before
    public void setup() {
        System.out.println("\n---------------------------------");
    }

    @Test
    public void saveEventWithoutAggregate() {

        final CartCreatedEvent cce = new CartCreatedEvent();

        final String aggregateId = eventStore.save(cce);
        assertNotNull(aggregateId);
        System.out.println(aggregateId);

        final List<Event> events = eventStore.retrieveForAggregate(aggregateId);
        assertNotNull(events);
        assertEquals(1, events.get(0).getVersion());
        assertEquals("CartCreatedEvent", events.get(0).getType());
    }

    @Test
    public void retrieveOrderedEvents() {

        final DomainEvent e = new CartCreatedEvent("test");
        final String aggId = eventStore.save(e);

        for (int i = 1; i <= 12; i++)
            eventStore.save(itemAddedEvent(), aggId);

        final List<Event> events = eventStore.retrieveForAggregate(aggId);
        events.forEach(System.out::println);

        assertEquals(13, events.size());
        assertTrue("Events not ordered by version", eventsOrderedByVersion(events));
    }

    @Test(expected = NoSuchAggregateException.class)
    public void saveEventInvalidAggregateId() {

        eventStore.save(new CartCreatedEvent("test"), "foobar-shit-2232322");
    }

    private boolean eventsOrderedByVersion(List<Event> events) {

        long expectedVersion = 1;
        for (final Event e : events) {

            System.out.printf("event %s, expected version: %d, actual: %d\n",
                    e.getId(), expectedVersion, e.getVersion());

            if (e.getVersion() != expectedVersion)
                return false;

            expectedVersion++;
        }
        return true;
    }

    private ItemAddedEvent itemAddedEvent() {

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        final BigDecimal bd = new BigDecimal(random.nextDouble(1.11, 333.33));
        final String price = bd.setScale(2, RoundingMode.HALF_UP).toString();

        final String sku = Integer.toString(random.nextInt(11111, 99999));

        final ItemAddedEvent e = new ItemAddedEvent();
        e.setItem(new Item(sku, price, "EUR"));

        return e;
    }
}
