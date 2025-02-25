package de.chris.fun.eventfun.query;

import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.store.EventRepository;

/**
 * @author Christian Wander
 *
 */
public class QueryService {

    @Autowired
    private EventRepository<Cart> eventstore;

    public Cart getCart(String id) {

        if (!eventstore.aggregateExists(id))
            throw new CartNotFoundException(id);

        return eventstore.replayFor(Cart.class, id);
    }

}
