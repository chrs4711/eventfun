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
package de.chris.fun.eventfun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domainevents.CartCreatedEvent;
import de.chris.fun.eventfun.store.Event;
import de.chris.fun.eventfun.store.EventStore;

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
    public void saveEventToStore() {

        final CartCreatedEvent cce = new CartCreatedEvent();

        final String aggregateId = eventStore.save(cce, "");
        assertNotNull(aggregateId);
        System.out.println(aggregateId);

        final List<Event> events = eventStore.retrieveForAggregate(aggregateId);
        assertNotNull(events);
        assertEquals(1, events.get(0).getVersion());

    }

}
