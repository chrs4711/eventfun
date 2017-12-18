package de.chris.fun.eventfun.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.domainevents.CartCreated;
import de.chris.fun.eventfun.domainevents.ItemAdded;
import de.chris.fun.eventfun.domainevents.ItemRemoved;
import de.chris.fun.eventfun.dtos.Item;
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

        final ItemRemoved e = new ItemRemoved();
        e.setSku(sku);

        return eventStore.save(e, cartId);
    }
}
