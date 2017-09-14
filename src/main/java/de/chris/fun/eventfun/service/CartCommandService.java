package de.chris.fun.eventfun.service;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.events.CartCreatedEvent;

@Component
public class CartCommandService {

    private final Logger logger = LoggerFactory.getLogger(CartCommandService.class);

    /**
     * Create a cart and fire the CartCreatedEvent?!
     * 
     * @return
     */
    public Long createCart() {

        // TODO: create a new aggregate (the id of the cart?!)
        // 
        // final Long cartId = new Random().nextLong();
        // final CartCreatedEvent e = new CartCreatedEvent(cartId);
        // logger.debug("event: " + e);
        // // save the event in the repository
        // 
        // logger.error("not implemented yet");
        // return cartId;
    	return null;
    }

    public void addItemToCart(String cartId, Item item) {
        logger.error("not implemented yet");
    }

    public void deleteItemFromCart(String sku, String cartId) {
        logger.error("not implemented yet");
    }

}
