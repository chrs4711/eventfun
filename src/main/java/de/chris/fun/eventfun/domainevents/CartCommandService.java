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
package de.chris.fun.eventfun.domainevents;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.store.Aggregate;
import de.chris.fun.eventfun.store.AggregateRepository;
import de.chris.fun.eventfun.store.Event;
import de.chris.fun.eventfun.store.EventRepository;

/**
 * @author Christian Wander
 *
 */
public class CartCommandService {

    @Autowired
    private AggregateRepository aggRepo;

    @Autowired
    private EventRepository eventRepo;

    public void createCart() {
        final Aggregate a = new Aggregate();
        a.setAggregateId(UUID.randomUUID().toString());

        final Event e = new Event();
        e.setId(UUID.randomUUID().toString());
        e.setAggregateId(a.getAggregateId());

        final CartCreatedEvent cce = new CartCreatedEvent();
        e.setData(serialize(cce));

        aggRepo.save(a);
        eventRepo.save(e);
    }

    public void addItemToCart(String cartId, Item item) {

        final Aggregate cartAgg = aggRepo.findOne(cartId);

        if (cartAgg == null)
            throw new RuntimeException("cart doesn't exist");

        // find next version for the aggreate

        // save the new event with the new version

    }

    private String serialize(CartCreatedEvent cce) {
        return "fake foo fake";
    }

}
