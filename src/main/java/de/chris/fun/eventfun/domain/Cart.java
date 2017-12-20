package de.chris.fun.eventfun.domain;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.chris.fun.eventfun.domainevents.CartCreated;
import de.chris.fun.eventfun.domainevents.ItemAdded;
import de.chris.fun.eventfun.domainevents.ItemRemoved;
import de.chris.fun.eventfun.store.DomainEvent;

/**
 * Represents the shopping cart. No DTO!
 * 
 * @author chris
 *
 */
public class Cart {

    private String createdBy;
    private List<Item> items = new ArrayList<>();

    private static final Logger logger = LoggerFactory.getLogger(Cart.class);

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart [createdBy=" + createdBy + ", items=" + items + "]";
    }

    public Item getItemForSku(String sku) {
        for (final Item i : items)
            if (sku.equals(i.getSku()))
                return i;

        return null;
    }

    public static Cart replay(List<DomainEvent> events) {

        Cart cart = new Cart();

        for (final DomainEvent e : events)
            cart = applyEvent(cart, e);

        return cart;
    }

    private static Cart applyEvent(Cart cart, DomainEvent e) {

        logger.debug("replaying event: " + e);

        if (e instanceof CartCreated) {
            cart.setCreatedBy(((CartCreated) e).getCreatedBy());
            return cart;
        }

        if (e instanceof ItemAdded) {
            cart.items.add(((ItemAdded) e).getItem());
            return cart;
        }

        if (e instanceof ItemRemoved) {
            final Item i = cart.getItemForSku(((ItemRemoved) e).getSku());

            if (i == null) {
                logger.warn("Cannot remove nonexisting item from cart " + e);
                return cart;
            }

            cart.items.remove(i);
            return cart;
        }

        logger.warn("Invalid event encountered during replay: {}", e);
        return cart;
    }

}
