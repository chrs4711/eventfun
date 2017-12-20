package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.domain.Item;
import de.chris.fun.eventfun.store.DomainEvent;

/**
 * @author Christian Wander
 *
 */
public class ItemAdded implements DomainEvent {

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ItemAddedEvent [item=" + item + "]";
    }

    @Override
    public <T> T apply(T domainObject) {

        final Cart cart = (Cart) domainObject;

        cart.getItems().add(item);

        return domainObject;
    }

}
