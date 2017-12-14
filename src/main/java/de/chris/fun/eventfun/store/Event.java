package de.chris.fun.eventfun.store;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    private String id;

    private String aggregateId;

    private int version;

    private String data;

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAggregateId() {
        return aggregateId;
    }

    public void setAggregateId(String aggregateId) {
        this.aggregateId = aggregateId;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

}
