package de.chris.fun.eventfun.store.serialize;

public class DomainEventSerializationException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    DomainEventSerializationException(Throwable e) {
        super(e);
    }

    DomainEventSerializationException(String message) {
        super(message);
    }
}
