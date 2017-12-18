package de.chris.fun.eventfun.domainevents;

import de.chris.fun.eventfun.store.DomainEvent;

public class PaymentAdded implements DomainEvent {

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

}
