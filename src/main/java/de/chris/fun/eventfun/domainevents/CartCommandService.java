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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.chris.fun.eventfun.dtos.Item;
import de.chris.fun.eventfun.store.SpringDataJPAEventStore;

/**
 * @author Christian Wander
 *
 */
public class CartCommandService {

    @Autowired
    private SpringDataJPAEventStore eventStore;

    private static final Logger logger = LoggerFactory.getLogger(CartCommandService.class);

    public String createCart(String creator) {

        final String cartId = eventStore.save(new CartCreatedEvent(creator), "");
        logger.debug("created new cart with id {}", cartId);

        return cartId;
    }

    public void addItemToCart(String cartId, Item item) {

    }

}
