/**
 * This code is property of:
 *
 * Hamm-Reno Group GmbH & Co. KG
 * Am Tie 7
 * D-49086 Osnabrück
 * Telefon: +49 (0)541 / 9584-0
 * Telefax: +49 (0)541 / 9584-9221
 *
 * (c) 2017 - 2017 all rights reserved.
 * 
 */
package de.chris.fun.eventfun.store.serialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import de.chris.fun.eventfun.store.DomainEvent;

/**
 * @author Christian Wander
 *
 */
public class JsonSerializ0r implements DomainEventSerializ0r {

    private final ObjectMapper objectMapper = new ObjectMapper();

    /*
     * (non-Javadoc)
     * 
     * @see
     * de.chris.fun.eventfun.store.DomainEventSerializ0r#serialize(de.chris.fun.
     * eventfun.store.DomainEvent)
     */
    @Override
    public String serialize(DomainEvent event) {

        try {
            return objectMapper.writeValueAsString(event);
        } catch (final JsonProcessingException e) {
            // TODO Auto-generated catch block
            return "shit";
        }
    }

}
