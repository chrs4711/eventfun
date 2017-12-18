package de.chris.fun.eventfun.store;

/**
 * @author Christian Wander
 *
 */
public class NoSuchAggregateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoSuchAggregateException(String message) {
        super(message);
    }

}
