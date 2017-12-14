package de.chris.fun.eventfun;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.chris.fun.eventfun.store.Aggregate;
import de.chris.fun.eventfun.store.AggregateRepository;
import de.chris.fun.eventfun.store.Event;
import de.chris.fun.eventfun.store.EventRepository;

@SpringBootApplication
public class EventfunApplication implements CommandLineRunner {
    
    @Autowired
    private AggregateRepository aggRepo;
    
    @Autowired
    private EventRepository eventRepo;

	public static void main(String[] args) {
		SpringApplication.run(EventfunApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("working...");
        
        Aggregate agg = new Aggregate();
        agg.setAggregateId(UUID.randomUUID().toString());
        agg.setVersion(0);
        
        List<Event> events = generateEvents(10, agg);
        
        // -----
       
        eventRepo.save(events);
        agg.setVersion(9);
        aggRepo.save(agg);

        // -----
        
        System.out.println("ok");
        
    }

    private List<Event> generateEvents(int j, Aggregate agg) {
        
        List<Event> events = new LinkedList<>();
        
        for (int i = 0; i < j; i++) {
            
            Event event = new Event();
            event.setId(UUID.randomUUID().toString());
            event.setAggregateId(agg.getAggregateId());
            event.setVersion(i);
            event.setData("-- whatever --");
            
            events.add(event);
        }
        
        return events;
    }

}
