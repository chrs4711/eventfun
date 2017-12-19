/**
 * This code is property of:
 *
 * Hamm-Reno Group GmbH & Co. KG
 * Am Tie 7
 * D-49086 Osnabrück
 * Telefon: +49 (0)541 / 9584-0
 * Telefax: +49 (0)541 / 9584-9221
 *
 * (c) 2017 - 2017 all rights reserved.
 * 
 */
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
