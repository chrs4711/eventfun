package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.store.DomainEvent;

/**
 * @author Christian Wander
 *
 */
public class CartCreatedEvent implements DomainEvent {

    private String createdBy;

    public CartCreatedEvent() {
        createdBy = "nobody";
    }

    public CartCreatedEvent(String createdBy) {
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

}
