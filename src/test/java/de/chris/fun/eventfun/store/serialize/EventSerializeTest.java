package de.chris.fun.eventfun.store.serialize;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.domain.events.CartCreated;
import de.chris.fun.eventfun.store.DomainEvent;
import de.chris.fun.eventfun.store.Event;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
public class EventSerializeTest {

    private final DomainEventSerializ0r<Cart> s = new JsonSerializ0r<>();

    private static final String CART_CREATED_JSON = "{\"createdBy\":\"nobody\"}";

    @Before
    public void setup() {
        s.setKnownDomainEventTypes(Arrays.asList(CartCreated.class));
    }

    @Test
    public void test() {

        final CartCreated e = new CartCreated("nobody");
        final String actual = s.serialize(e);
        assertEquals(CART_CREATED_JSON, actual);

        System.out.println();
        System.out.println(actual);
    }

    @Test
    public void testDeserialize() {

        final Event e = new Event();
        e.setId("foo");
        e.setAggregateId("fooAggBar");
        e.setVersion(1);
        e.setType("CartCreated");
        e.setData(CART_CREATED_JSON);

        final DomainEvent<Cart> actual = s.deserialize(e);
        assertEquals("CartCreated", actual.getClass().getSimpleName());
    }

}
