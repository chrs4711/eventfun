package de.chris.fun.eventfun.store;

import javax.persistence.Entity;
import javax.persistence.Id;


@Entity
public class Aggregate {

    @Id
    private String aggregateId;
    
    private int version;

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

}
