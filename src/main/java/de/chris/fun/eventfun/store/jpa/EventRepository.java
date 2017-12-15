package de.chris.fun.eventfun.store.jpa;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import de.chris.fun.eventfun.store.Event;

public interface EventRepository extends CrudRepository<Event, String> {

    List<Event> findByAggregateIdOrderByVersion(String aggregateId);
    
}
