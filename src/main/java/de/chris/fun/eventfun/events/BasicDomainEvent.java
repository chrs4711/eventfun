package de.chris.fun.eventfun.events;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Stuff every domain event should have.
 * 
 * @author christian
 *
 */
@MappedSuperclass
public abstract class BasicDomainEvent {

    @Id
    @GeneratedValue
    @Column(name = "event_id", updatable = false)
    protected String eventId;

    @Column(name = "event_time")
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
