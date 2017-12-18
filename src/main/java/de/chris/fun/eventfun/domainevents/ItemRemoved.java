package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.store.DomainEvent;

public class ItemRemoved implements DomainEvent {

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

}
