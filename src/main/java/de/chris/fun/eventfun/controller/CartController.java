package de.chris.fun.eventfun.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import de.chris.fun.eventfun.command.CartCommandService;
import de.chris.fun.eventfun.domain.Item;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    CartCommandService commandService;

    @PostMapping("")
    public ResponseEntity<?> createNewCart() {

        return ResponseEntity.ok(commandService.createCart("implement me"));
    }

    @GetMapping("{cartId}")
    public ResponseEntity<?> getCartById(@PathVariable("cartId") String cartId) {

        return ResponseEntity.notFound().build();
    }

    @PostMapping("{cartId}/items")
    public ResponseEntity<?> addItemToCart(String cartId, Item item) {

        return ResponseEntity.ok(commandService.addItemToCart(cartId, item));
    }

    @DeleteMapping("{cartId}/items/{sku}")
    public ResponseEntity<?> removeItemFromCart(@PathVariable("cartId") String cartId,
            @PathVariable("sku") String sku) {

        return ResponseEntity.ok(commandService.removeItemFromCart(sku, cartId));
    }

}
