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
public abstract class CartEvent {

	@Column(name = "aggregate_id", updatable = false)
	protected String aggregateId;

	@Id
	@Column(name = "event_id")
	protected String eventId;
	
	@Column(name = "event_time")
	protected Date eventTimestamp;

	@Column(name = "data")
	protected String data;
	
	protected CartEvent() {
		// hibernate wants that
	}
	
	public CartEvent(String aggregateId) {
		this.aggregateId = aggregateId;
		eventTimestamp = new Date();
		eventId = UUID.randomUUID().toString();
	}
	
	public String getEventId() {
		return eventId;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getAggregateId() {
		return aggregateId;
	}

	public Date getEventTimestamp() {
		return eventTimestamp;
	}
}
