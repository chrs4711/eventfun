package de.chris.fun.eventfun;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import de.chris.fun.eventfun.events.CartCreatedEvent;
import de.chris.fun.eventfun.events.CartItemAddedEvent;
import de.chris.fun.eventfun.events.CartItemRemovedEvent;

@RunWith(JUnit4.class)
public class EventfunApplicationTests {

    @Test
    public void test() {
        final CartCreatedEvent cce = new CartCreatedEvent(1337L);
        System.out.println(cce);

        final CartItemAddedEvent ciae = new CartItemAddedEvent(1337L, "10005", "10.00", "EUR", "FU Spray");
        System.out.println(ciae);

        final CartItemRemovedEvent cire = new CartItemRemovedEvent(1337L, "10005", "10.00", "EUR", "FU Spray");
        System.out.println(cire);
    }

}
