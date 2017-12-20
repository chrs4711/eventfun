package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.domain.Item;
import de.chris.fun.eventfun.store.DomainEvent;

public class ItemRemoved implements DomainEvent<Cart> {

    private String sku;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @Override
    public String toString() {
        return "ItemRemoved [sku=" + sku + "]";
    }

    @Override
    public Cart apply(Cart domainObject) {

        final Cart cart = domainObject;

        final Item item = cart.getItemForSku(sku);

        if (item != null) {
            cart.getItems().remove(item);
        }

        return cart;
    }

}
