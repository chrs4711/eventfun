package de.chris.fun.eventfun.domain.events;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.store.DomainEvent;

/**
 * @author Christian Wander
 *
 */
public class CartCreated implements DomainEvent<Cart> {

    private String createdBy;

    public CartCreated() {
        createdBy = "nobody";
    }

    public CartCreated(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "CartCreatedEvent [createdBy=" + createdBy + "]";
    }

    @Override
    public Cart apply(Cart cart) {
        cart.setCreatedBy(createdBy);
        return cart;
    }

}
