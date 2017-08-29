package de.chris.fun.eventfun.events;

import javax.persistence.Entity;

@Entity
public class CartCreatedEvent extends BasicDomainEvent {

    private final Long cartId;

    public CartCreatedEvent(Long cartId) {
        this.cartId = cartId;
    }

    public Long getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        return "CartCreatedEvent [cartId=" + cartId + ", eventId=" + eventId + ", eventDate=" + eventDate + "]";
    }

}
