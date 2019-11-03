package de.chris.fun.eventfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import de.chris.fun.eventfun.command.CartCommandService;
import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.domain.Item;
import de.chris.fun.eventfun.query.QueryService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartCommandService commandService;

    @Autowired
    QueryService queryService;

    @PostMapping("")
    public ResponseEntity<?> createNewCart() {

        return ResponseEntity.ok(commandService.createCart("implement me"));
    }

    @GetMapping("{cartId}")
    public Cart getCartById(@PathVariable("cartId") String cartId) {

        return queryService.getCart(cartId);
    }

    @PostMapping("{cartId}/items")
    public ResponseEntity<?> addItemToCart(String cartId, @RequestBody Item item) {

        return ResponseEntity.ok(commandService.addItemToCart(cartId, item));
    }

    @DeleteMapping("{cartId}/items/{sku}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable("cartId") String cartId,
            @PathVariable("sku") String sku) {

        return ResponseEntity.ok(commandService.removeItemFromCart(sku, cartId));
    }

}
