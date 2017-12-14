package de.chris.fun.eventfun.store;

import org.springframework.data.repository.CrudRepository;

public interface AggregateRepository extends CrudRepository<Aggregate, String> {

}
