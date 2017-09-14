package de.chris.fun.eventfun.events;

public class CartItemAddedEvent extends CartEvent {

    private final Long cartId;
    private final String sku;
    private final String price;
    private final String currency;
    private final String desc;

    public CartItemAddedEvent(Long cartId, String sku, String price, String currency, String desc) {
        super(cartId.toString()); // XXX
        this.cartId = cartId;
        this.sku = sku;
        this.price = price;
        this.currency = currency;
        this.desc = desc;
    }

    public Long getCartId() {
        return cartId;
    }

    public String getSku() {
        return sku;
    }

    public String getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getDesc() {
        return desc;
    }

    @Override
    public String toString() {
        return "CartItemAddedEvent [cartId=" + cartId + ", sku=" + sku + ", price=" + price + ", currency=" + currency
                + ", desc=" + desc + ", eventId=" + aggregateId + ", eventDate=" + eventTimestamp + "]";
    }

}
