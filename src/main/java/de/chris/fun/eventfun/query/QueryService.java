package de.chris.fun.eventfun.query;

import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.store.EventStore;

/**
 * @author Christian Wander
 *
 */
public class QueryService {

    @Autowired
    private EventStore eventstore;

    public Cart getCart(String id) {

        if (!eventstore.aggregateExists(id))
            return null;

        return Cart.replay(eventstore.get(id));
    }

}
