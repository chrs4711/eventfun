package de.chris.fun.eventfun.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domain.events.CartCreated;
import de.chris.fun.eventfun.domain.events.ItemAdded;
import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.EventRepository;
import de.chris.fun.eventfun.store.memory.MemoryEventStore;
import de.chris.fun.eventfun.store.serialize.JsonSerializ0r;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
public class EventRepositoryTest {

    private EventRepository<Cart> eventRepo;

    @Before
    public void setup() {

        eventRepo = new EventRepository<>();

        final JsonSerializ0r<Cart> s = new JsonSerializ0r<>();
        s.setKnownDomainEventTypes(Arrays.asList(CartCreated.class, ItemAdded.class));
        eventRepo.setSerializer(s);
        eventRepo.setEventStore(new MemoryEventStore());
    }

    @Test
    public void replayEvents() {

        final DomainEvent<Cart> e = new CartCreated("test");
        final String aggId = eventRepo.save(e);

        for (int i = 1; i <= 12; i++)
            eventRepo.save(itemAddedEvent(), aggId);

        final Cart c = Cart.replay(eventRepo.get(aggId));
        assertNotNull(c);
        assertEquals(12, c.getItems().size());
        System.out.println(c);
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
