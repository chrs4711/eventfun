package de.chris.fun.eventfun.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Represents an item in a shopping cart. Used in the controller for
 * deserializing request bodies into it.
 * 
 * @author christian
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Item {

    private String sku;
    private String price;
    private String currency;

    public Item(String sku, String price, String currency) {
        super();
        this.sku = sku;
        this.price = price;
        this.currency = currency;
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

}
