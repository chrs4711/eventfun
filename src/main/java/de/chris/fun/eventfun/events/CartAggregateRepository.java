package de.chris.fun.eventfun.events;

import org.springframework.data.repository.CrudRepository;

public interface CartAggregateRepository extends CrudRepository<CartAggregate, String> {

}
