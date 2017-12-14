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
package de.chris.fun.eventfun.store;

/**
 * @author Christian Wander
 *
 */
public class NoSuchAggregateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public NoSuchAggregateException(String message) {
        super(message);
    }

}
