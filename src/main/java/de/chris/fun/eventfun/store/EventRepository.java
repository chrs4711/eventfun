package de.chris.fun.eventfun.store;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EventRepository extends CrudRepository<Event, String> {

    List<Event> findByAggregateIdOrderByVersion(String aggregateId);
    
}
