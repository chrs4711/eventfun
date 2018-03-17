package de.chris.fun.eventfun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.chris.fun.eventfun.domain.Cart;
import de.chris.fun.eventfun.store.serialize.DomainEventSerializ0r;
import de.chris.fun.eventfun.store.serialize.JsonSerializ0r;

@SpringBootApplication
public class EventfunApplication {

    public static void main(String[] args) {
        SpringApplication.run(EventfunApplication.class, args);
    }

    @Bean
    public DomainEventSerializ0r<Cart> eventSerializ0r() {
        return new JsonSerializ0r<>();
    }

}
