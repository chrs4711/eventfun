package de.chris.fun.eventfun.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domainevents.CartCreatedEvent;
import de.chris.fun.eventfun.domainevents.ItemAddedEvent;
import de.chris.fun.eventfun.dtos.Item;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class EventStoreTest {

    @Autowired
    private EventStore eventStore;

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
    public void multiEvents() {

        final DomainEvent e = new CartCreatedEvent("test");
        final String aggId = eventStore.save(e);

        eventStore.save(itemAddedEvent(), aggId);
        eventStore.save(itemAddedEvent(), aggId);
        eventStore.save(itemAddedEvent(), aggId);
        eventStore.save(itemAddedEvent(), aggId);

        final List<Event> events = eventStore.retrieveForAggregate(aggId);
        assertEquals(5, events.size());
    }

    @Test(expected = NoSuchAggregateException.class)
    public void saveEventInvalidAggregateId() {

        eventStore.save(new CartCreatedEvent("test"), "foobar-shit-2232322");
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
