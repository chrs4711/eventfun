package de.chris.fun.eventfun.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.chris.fun.eventfun.dtos.Cart;
import de.chris.fun.eventfun.dtos.Item;

@Component
public class CartService {

    @SuppressWarnings("unused")
    private final Logger logger = LoggerFactory.getLogger(CartService.class);

    public String createCart() {
        return "fake";
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
