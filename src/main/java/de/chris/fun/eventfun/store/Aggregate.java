package de.chris.fun.eventfun.store;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Aggregate {

    @Id
    private String aggregateId;

    private long version;

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Aggregate [aggregateId=" + aggregateId + ", version=" + version + "]";
    }

}
