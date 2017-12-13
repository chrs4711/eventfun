package de.chris.fun.eventfun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.chris.fun.eventfun.dtos.Cart;
import de.chris.fun.eventfun.dtos.Item;

@Component
public class CartService {

    private final Logger logger = LoggerFactory.getLogger(CartService.class);

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

    public Cart addItemToCart(String cartId, Item item) {
    	throw new RuntimeException("not implemented yet");
    }

    public Cart deleteItemFromCart(String sku, String cartId) {
    	throw new RuntimeException("not implemented yet");
    }

	public Cart getCart(String cartId) {
		throw new RuntimeException("not implemented yet");
	}

}
