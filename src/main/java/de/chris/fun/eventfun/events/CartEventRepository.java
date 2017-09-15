package de.chris.fun.eventfun.events;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CartEventRepository extends CrudRepository<CartEvent, String> {

    public List<CartEvent> findByAggregateIdOrderByEventTimestampAsc(String aggregateId);

}
