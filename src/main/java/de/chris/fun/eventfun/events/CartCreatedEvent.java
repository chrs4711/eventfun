package de.chris.fun.eventfun.events;

import javax.persistence.Entity;

@Entity
public class CartCreatedEvent extends CartEvent {
	
	@SuppressWarnings("unused")
	private CartCreatedEvent() {
		// hibernate wants that.
	}
	
	public CartCreatedEvent(String aggregateId) {
		super(aggregateId);
	}

	@Override
	public String toString() {
		return "CartCreatedEvent [aggregateId=" + aggregateId + ", eventId=" + eventId + ", eventTimestamp="
				+ eventTimestamp + ", data=" + data + "]";
	}

}
