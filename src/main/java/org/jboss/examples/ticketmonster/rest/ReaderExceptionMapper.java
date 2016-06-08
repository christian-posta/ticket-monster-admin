package org.jboss.examples.ticketmonster.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * A JAX-RS ExceptionMapper that re-maps RESTEasy ReaderExceptions
 * to ensure that an appropriate response is generated for the mediatype supported by the client.
 *
 * By default, a text/html response is generated that would violate HTTP semantics.
 */
@Provider
public class ReaderExceptionMapper implements ExceptionMapper<Exception> {

    @Context
    private HttpHeaders headers;


    @Override
    public Response toResponse(Exception exception) {
        Map<String, Object> responseEntity = new HashMap<String, Object>();
        responseEntity.put("errors", Collections.singletonList("The submitted request is invalid. Please retry after correcting the input values."));
        responseEntity.put("exception", exception.toString());
        return Response.status(Response.Status.BAD_REQUEST).
                entity(responseEntity).
                type(headers.getMediaType()).
                build();
    }
}
