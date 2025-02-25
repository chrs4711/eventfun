package de.chris.fun.eventfun.domain.events;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.store.DomainEvent;

public class PaymentAdded implements DomainEvent<Cart> {

    private String amount;
    private String currency;
    private String method;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "PaymentAdded [amount=" + amount + ", currency=" + currency + ", method=" + method + "]";
    }

    @Override
    public Cart apply(Cart cart) {
        // TODO Auto-generated method stub
        System.out.println("Payment not implemented yet in " + this.getClass().getName());
        return cart;
    }

}
