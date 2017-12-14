package de.chris.fun.eventfun.store.serialize;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domainevents.CartCreatedEvent;

/**
 * @author Christian Wander
 *
 */
@RunWith(SpringRunner.class)
public class EventSerializeTest {

    private final DomainEventSerializ0r s = new JsonSerializ0r();

    private static final String CART_CREATED_JSON = "{\"createdBy\":\"nobody\"}";

    @Test
    public void test() {

        final CartCreatedEvent e = new CartCreatedEvent("nobody");
        final String actual = s.serialize(e);
        assertEquals(CART_CREATED_JSON, actual);

        System.out.println();
        System.out.println(actual);
    }

}
