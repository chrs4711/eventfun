package de.chris.fun.eventfun.events;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Stuff every domain event should have.
 * 
 * @author christian
 *
 */
@Entity
public abstract class BasicDomainEvent {

    @Id
    @GeneratedValue
    @Column(name = "id", updatable = false)
    protected String eventId;

    @Column(name = "time")
    protected Date eventDate;

    @Column(name = "payload")
    protected String payload;

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
