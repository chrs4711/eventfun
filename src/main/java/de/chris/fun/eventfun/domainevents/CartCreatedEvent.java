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

}
