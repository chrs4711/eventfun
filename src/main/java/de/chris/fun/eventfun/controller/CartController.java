package de.chris.fun.eventfun.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.service.CartCommandService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartCommandService cartCommandService;

    private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("")
    public ResponseEntity<?> createNewCart() {
        logger.debug("request create new cart");

        final Long cartId = cartCommandService.createCart();

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable("cartId") String cartId) {

        logger.debug("cart with id {} requested", cartId);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "{cartId}/items", method = RequestMethod.POST)
    public ResponseEntity<?> addItemToCart(String cartId, Item item) {

        logger.debug("request adding item with sku {} to cart {}", item.getSku(), cartId);

        cartCommandService.addItemToCart(cartId, item);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "{cartId}/items/{sku}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemFromCart(@PathVariable("cartId") String cartId,
            @PathVariable("sku") String sku) {

        logger.debug("request deleting sku {} from cart {}", sku, cartId);

        cartCommandService.deleteItemFromCart(sku, cartId);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
