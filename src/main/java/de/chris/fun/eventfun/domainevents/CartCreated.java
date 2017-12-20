package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.store.DomainEvent;

/**
 * @author Christian Wander
 *
 */
public class CartCreated implements DomainEvent {

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
    public <T> T apply(T domainObject) {

        final Cart cart = (Cart) domainObject;

        cart.setCreatedBy(createdBy);

        return domainObject;
    }

}
