package de.chris.fun.eventfun.query;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.dtos.Cart;
import de.chris.fun.eventfun.store.DomainEvent;
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

        return null;

    }

    private Cart replay(List<DomainEvent> events) {

        return null;
    }

}
