package de.chris.fun.eventfun.query;

public class CartNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Thrown when a cart could not be found
     * 
     * @param id
     *            the id of the cart
     */
    public CartNotFoundException(String id) {

        super(String.format("cart %s not found", id));
    }
}
