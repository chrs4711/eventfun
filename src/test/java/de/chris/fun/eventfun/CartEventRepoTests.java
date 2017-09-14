package de.chris.fun.eventfun;

import java.util.UUID;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.chris.fun.eventfun.events.CartAggregate;
import de.chris.fun.eventfun.events.CartAggregateRepository;
import de.chris.fun.eventfun.events.CartCreatedEvent;
import de.chris.fun.eventfun.events.CartEventRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CartEventRepoTests {

	@Autowired
	private CartEventRepository cartEventRepo;
	
	@Autowired
	private CartAggregateRepository aggregateRepo;

	@Before
	public void before() {
		System.out.println("-----------------------------------");
		System.out.println();
	}

	@After
	public void after() {
		System.out.println();
		System.out.println("-----------------------------------");
	}

	@Test
	public void testStoreEvent() {
		
		// create a new aggregate!
		final CartAggregate cagg = new CartAggregate(UUID.randomUUID().toString(), 0);

		// new event
		final CartCreatedEvent cce = new CartCreatedEvent(cagg.getAggregateId());
		System.out.println(cce);

		aggregateRepo.save(cagg);
		cartEventRepo.save(cce);

		CartCreatedEvent tmp = (CartCreatedEvent) cartEventRepo.findOne(cce.getEventId());
		System.out.println("retrieved cart event: " + tmp);
		
		CartAggregate tmp2 = aggregateRepo.findOne(cagg.getAggregateId());
		System.out.println("retrieved aggregate: " + tmp2);

	}

}
