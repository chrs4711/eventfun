package de.chris.fun.eventfun.store;

public class ReplayException extends RuntimeException {
    public ReplayException(ReflectiveOperationException e) {
        super("Unable to replay events", e);
    }
}
