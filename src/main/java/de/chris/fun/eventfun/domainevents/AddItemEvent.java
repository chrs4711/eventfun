package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.dtos.Item;

/**
 * @author Christian Wander
 *
 */
public class AddItemEvent {

    private String cartId;

    private Item item;

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

}
