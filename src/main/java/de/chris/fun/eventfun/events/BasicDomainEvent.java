package de.chris.fun.eventfun.events;

import java.util.Date;
import java.util.UUID;

/**
 * Stuff every domain event should have.
 * 
 * @author christian
 *
 */
public abstract class BasicDomainEvent {

    protected String eventId;

    protected Date eventDate;

    public BasicDomainEvent() {
        eventDate = new Date();
        eventId = UUID.randomUUID().toString();
    }

    public String getEventId() {
        return eventId;
    }

    public Date getEventDate() {
        return eventDate;
    }
}
