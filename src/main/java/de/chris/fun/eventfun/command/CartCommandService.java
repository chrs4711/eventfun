package de.chris.fun.eventfun.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.domainevents.CartCreated;
import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.store.EventStore;

/**
 * @author Christian Wander
 *
 */
public class CartCommandService {

    @Autowired
    private EventStore eventStore;

    private static final Logger logger = LoggerFactory.getLogger(CartCommandService.class);

    public String createCart(String creator) {

        final String cartId = eventStore.save(new CartCreated(creator), "");
        logger.debug("created new cart with id {}", cartId);

        return cartId;
    }

    public void addItemToCart(String cartId, Item item) {

    }

}
