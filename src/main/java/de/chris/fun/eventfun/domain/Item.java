package de.chris.fun.eventfun.domain;

/**
 * An item which sits in a cart.
 * 
 * @author chris
 *
 */
public class Item {

    private String sku;
    private String price;
    private String currency;
    private String description;

    public Item() {

    }

    public Item(String sku, String price, String currency, String description) {
        super();
        this.sku = sku;
        this.price = price;
        this.currency = currency;
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item [sku=" + sku + ", price=" + price + ", currency=" + currency + ", description=" + description
                + "]";
    }

}
