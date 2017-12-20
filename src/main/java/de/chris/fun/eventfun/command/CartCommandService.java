package de.chris.fun.eventfun.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.domain.Item;
import de.chris.fun.eventfun.domain.events.CartCreated;
import de.chris.fun.eventfun.domain.events.ItemAdded;
import de.chris.fun.eventfun.domain.events.ItemRemoved;
import de.chris.fun.eventfun.store.EventStore;

/**
 * @author Christian Wander
 * 
 *         Receives commands (objects?) from clients, performs business
 *         validation/logic and fires the corresponding events when all is fine.
 *
 */
public class CartCommandService {

    @Autowired
    private EventStore eventStore;

    private static final Logger logger = LoggerFactory.getLogger(CartCommandService.class);

    public String createCart(String createdBy) {

        final String cartId = eventStore.save(new CartCreated(createdBy));
        logger.debug("created new cart with id {}", cartId);

        return cartId;
    }

    public String addItemToCart(String cartId, Item item) {

        if (!eventStore.aggregateExists(cartId))
            throw new CommandException(String.format("cart %s does not exist.", cartId));

        final ItemAdded e = new ItemAdded();
        e.setItem(item);

        return eventStore.save(e, cartId);
    }

    public String removeItemFromCart(String cartId, String sku) {

        if (!eventStore.aggregateExists(cartId))
            throw new CommandException(String.format("cart %s does not exist.", cartId));

        // Can (should) we find out if the item to be deleted exists in the cart?

        // option1: ask event store (gets very slow very fast ..)
        // option2: ask the materialized view of this cart (eventually consistent)
        // option3: let the reader of the events worry about it. That would mean that no
        // error is raised and everything looks fine.

        final ItemRemoved e = new ItemRemoved();
        e.setSku(sku);

        return eventStore.save(e, cartId);
    }
}
