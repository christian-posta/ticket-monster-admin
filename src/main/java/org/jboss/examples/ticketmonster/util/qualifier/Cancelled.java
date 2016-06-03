package org.jboss.examples.ticketmonster.util.qualifier;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;

/**
 * {@link Qualifier} to mark a Booking as cancelled.
 * 
 * @author Christian Sadilek <csadilek@redhat.com>
 */
@Qualifier
@Target({ElementType.FIELD,ElementType.PARAMETER,ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Cancelled {

}
