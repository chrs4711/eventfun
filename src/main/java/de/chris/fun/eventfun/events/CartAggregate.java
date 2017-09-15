package de.chris.fun.eventfun.events;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Every cart in the system will have an aggregate.
 * 
 * @author chris
 *
 */
@Entity
public class CartAggregate {

    @Id
    @Column(name = "aggregate_id")
    private String aggregateId;

    @Column(name = "version")
    private int version;

    protected CartAggregate() {

    }

    public CartAggregate(String aggregateId, int version) {
        this.aggregateId = aggregateId;
        this.version = version;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "CartAggregate [aggregateId=" + aggregateId + ", version=" + version + "]";
    }

}
