package de.chris.fun.eventfun.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.domain.Item;
import de.chris.fun.eventfun.domainevents.CartCreated;
import de.chris.fun.eventfun.domainevents.ItemAdded;
import de.chris.fun.eventfun.store.memory.MemoryEventStore;
import de.chris.fun.eventfun.store.serialize.JsonSerializ0r;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
public class EventStoreTest {

    private final EventStore eventStore = new MemoryEventStore();

    @Before
    public void setup() {

        final JsonSerializ0r s = new JsonSerializ0r();
        s.setKnownDomainEventTypes(Arrays.asList(CartCreated.class, ItemAdded.class));
        eventStore.setSerializ0r(s);
    }

    @Test
    public void saveEventWithoutAggregate() {

        final CartCreated cce = new CartCreated();
        final String eventType = cce.getClass().getSimpleName();

        final String aggregateId = eventStore.save(cce);
        assertNotNull(aggregateId);
        System.out.println(aggregateId);

        final List<Event> events = eventStore.getRawEvents(aggregateId);
        assertNotNull(events);
        assertEquals(1, events.get(0).getVersion());
        assertEquals(eventType, events.get(0).getType());
    }

    @Test
    public void retrieveOrderedEvents() {

        final DomainEvent<Cart> e = new CartCreated("test");
        final String aggId = eventStore.save(e);

        for (int i = 1; i <= 12; i++)
            eventStore.save(itemAddedEvent(), aggId);

        final List<Event> events = eventStore.getRawEvents(aggId);
        events.forEach(System.out::println);

        assertEquals(13, events.size());
        assertTrue("Events not ordered by version", eventsOrderedByVersion(events));

        final Cart c = Cart.replay(eventStore.get(aggId));
        System.out.println(c);

    }

    @Test(expected = NoSuchAggregateException.class)
    public void saveEventInvalidAggregateId() {

        eventStore.save(new CartCreated("test"), "foobar-shit-2232322");
    }

    @Test
    public void testAggregateExists() {
        final String id = eventStore.save(new CartCreated("footest"));

        assertTrue(eventStore.aggregateExists(id));
        assertTrue(!eventStore.aggregateExists("foobar-shit-123131"));
    }

    @Test
    public void testRetrieveDomainEvents() {

        final String aggId = eventStore.save(new CartCreated("test"));

        final List<DomainEvent<Cart>> domainEvents = eventStore.get(aggId);
        assertNotNull(domainEvents);
        assertEquals("CartCreated", domainEvents.get(0).getClass().getSimpleName());

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

    private ItemAdded itemAddedEvent() {

        final ThreadLocalRandom random = ThreadLocalRandom.current();

        final BigDecimal bd = new BigDecimal(random.nextDouble(1.11, 333.33));
        final String price = bd.setScale(2, RoundingMode.HALF_UP).toString();

        final String sku = Integer.toString(random.nextInt(11111, 99999));

        final ItemAdded e = new ItemAdded();
        e.setItem(new Item(sku, price, "EUR", "dummy text"));

        return e;
    }
}
