package de.chris.fun.eventfun.store.jpa;

import org.springframework.data.repository.CrudRepository;

import de.chris.fun.eventfun.store.Aggregate;

public interface AggregateRepository extends CrudRepository<Aggregate, String> {

}
