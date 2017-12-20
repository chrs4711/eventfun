package de.chris.fun.eventfun.command;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.domain.Item;
import de.chris.fun.eventfun.domain.events.ItemAdded;
import de.chris.fun.eventfun.domain.events.ItemRemoved;
import de.chris.fun.eventfun.store.EventStore;

@RunWith(SpringRunner.class)
public class CartCommandTest {

    private static final String NON_EXISTING_ID = "non-existing-id";

    private static final String EXISTING_AGG_ID = "existing-id";

    @Mock
    private EventStore eventStore;

    @InjectMocks
    private CartCommandService service;

    @Before
    public void prepare() {
        MockitoAnnotations.initMocks(this);

        when(eventStore.aggregateExists(NON_EXISTING_ID)).thenReturn(false);
        when(eventStore.aggregateExists(EXISTING_AGG_ID)).thenReturn(true);
        when(eventStore.save(any(ItemRemoved.class), eq(EXISTING_AGG_ID))).thenReturn(EXISTING_AGG_ID);
        when(eventStore.save(any(ItemAdded.class), eq(EXISTING_AGG_ID))).thenReturn(EXISTING_AGG_ID);
    }

    @Test
    public void createCart() {

        when(eventStore.save(any())).thenReturn("some-id");

        final String cartId = service.createCart("nobody");

        assertEquals("some-id", cartId);
    }

    @Test
    public void addItemToExistingCart() {

        final String actual = service.addItemToCart(EXISTING_AGG_ID, new Item("12345", "12.34", "OIR", "dummy text"));
        assertEquals(EXISTING_AGG_ID, actual);
    }

    @Test
    public void removeItemFromExistingCart() {

        final String actual = service.removeItemFromCart(EXISTING_AGG_ID, "54321");
        assertEquals(EXISTING_AGG_ID, actual);
    }

    @Test(expected = CommandException.class)
    public void addItemToNonexistingCart() {
        service.addItemToCart(NON_EXISTING_ID, new Item("1234", "23.23", "EUR", "dummy text"));
    }

    @Test(expected = CommandException.class)
    @Ignore("not implemented yet.")
    public void removeNonexistingItemFromExistingCart() {
        service.removeItemFromCart(EXISTING_AGG_ID, "12345");
    }

    @Test(expected = CommandException.class)
    public void removeItemFromNonexistingCart() {
        service.removeItemFromCart(NON_EXISTING_ID, "54321");
    }

}
