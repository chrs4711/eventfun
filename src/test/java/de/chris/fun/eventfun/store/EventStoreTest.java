package de.chris.fun.eventfun.store;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.store.memory.MemoryEventStore;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
public class EventStoreTest {

    private EventStore eventStore;

    @Before
    public void setup() {
        eventStore = new MemoryEventStore();
    }

    @Test
    public void saveEventWithoutAggregate() {

        final String aggregateId = eventStore.save("testdata", "testType");
        assertNotNull(aggregateId);
        System.out.println(aggregateId);

        final List<Event> events = eventStore.getRawEvents(aggregateId);
        assertNotNull(events);
        assertEquals(1, events.get(0).getVersion());
        assertEquals("testType", events.get(0).getType());
    }

    @Test(expected = NoSuchAggregateException.class)
    public void saveEventInvalidAggregateId() {

        eventStore.save("someData", "someType", "no-such-id");
    }

    @Test
    public void testAggregateExists() {
        final String id = eventStore.save("somedata", "sometype");

        assertTrue(eventStore.aggregateExists(id));
        assertTrue(!eventStore.aggregateExists("foobar-shit-123131"));
    }

    @Test
    public void testEventsOrderedByVersion() {

        final String id = eventStore.save("foodata", "sometype");

        for (int i = 1; i < 12; i++) {
            eventStore.save("foodata " + i, "sometype", id);
        }

        final List<Event> events = eventStore.getRawEvents(id);
        assertNotNull(events);
        assertTrue(eventsOrderedByVersion(events));

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

}
