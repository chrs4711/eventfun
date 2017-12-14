package de.chris.fun.eventfun.events;

import org.springframework.data.repository.CrudRepository;

public interface AggregateRepository extends CrudRepository<Aggregate, String> {

}
