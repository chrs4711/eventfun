package de.chris.fun.eventfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    // private static final Logger logger = LoggerFactory.getLogger(CartController.class);

    @PostMapping("")
    public ResponseEntity<?> createNewCart() {
        
        return ResponseEntity.ok(cartService.createCart());
    }

    @GetMapping("{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable("cartId") String cartId) {

        return ResponseEntity.ok(cartService.getCart(cartId));
    }

    @RequestMapping(value = "{cartId}/items", method = RequestMethod.POST)
    public ResponseEntity<?> addItemToCart(String cartId, Item item) {
        cartService.addItemToCart(cartId, item);

        return ResponseEntity.ok(cartService.addItemToCart(cartId, item));
    }

    @RequestMapping(value = "{cartId}/items/{sku}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteItemFromCart(@PathVariable("cartId") String cartId,
            @PathVariable("sku") String sku) {

        

        return ResponseEntity.ok(cartService.deleteItemFromCart(sku, cartId));
    }

}
