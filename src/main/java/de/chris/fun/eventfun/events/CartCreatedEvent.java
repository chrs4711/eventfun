package de.chris.fun.eventfun.events;

public class CartCreatedEvent extends BasicDomainEvent {

    private final String cartId;

    public CartCreatedEvent(String cartId) {
        this.cartId = cartId;
    }

    public String getCartId() {
        return cartId;
    }

    @Override
    public String toString() {
        return "CartCreatedEvent [cartId=" + cartId + ", eventId=" + eventId + ", eventDate=" + eventDate + "]";
    }

}
