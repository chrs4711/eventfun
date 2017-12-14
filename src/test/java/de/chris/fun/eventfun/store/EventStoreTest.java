package de.chris.fun.eventfun.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domainevents.CartCreatedEvent;

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

    @Test(expected = NoSuchAggregateException.class)
    public void saveEventInvalidAggregateId() {

        eventStore.save(new CartCreatedEvent("test"), "foobar-shit-2232322");
    }

}
