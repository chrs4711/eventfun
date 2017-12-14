package de.chris.fun.eventfun.store;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Event {

    @Id
    private String id;

    private String aggregateId;

    private long version;

    private String type;

    private String data;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
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

    @Override
    public String toString() {
        return "Event [id=" + id + ", aggregateId=" + aggregateId + ", version=" + version + ", type=" + type
                + ", data=" + data + "]";
    }

}
