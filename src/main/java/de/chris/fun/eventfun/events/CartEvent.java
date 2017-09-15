package de.chris.fun.eventfun.events;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * The event which gets persisted to the event store.
 * 
 * @author christian
 *
 */
@Entity(name = "cart_event")
public class CartEvent {

    @Column(name = "aggregate_id", updatable = false)
    protected String aggregateId;

    @Id
    @Column(name = "event_id")
    protected String eventId;

    @Column(name = "version")
    protected int version;

    @Column(name = "event_time")
    protected Date eventTimestamp;

    @Column(name = "data")
    protected String data;

    protected CartEvent() {
        // hibernate wants that
    }

    public CartEvent(String aggregateId, String data) {
        this.aggregateId = aggregateId;
        eventTimestamp = new Date();
        eventId = UUID.randomUUID().toString();
        this.data = data;
    }

    public String getEventId() {
        return eventId;
    }

    public String getData() {
        return data;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public Date getEventTimestamp() {
        return eventTimestamp;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CartEvent [aggregateId=" + aggregateId + ", eventId=" + eventId + ", version=" + version
                + ", eventTimestamp=" + eventTimestamp + ", data=" + data + "]";
    }

}
