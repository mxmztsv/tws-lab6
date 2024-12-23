package ru.mxmztsv.app.rest.exception;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import ru.mxmztsv.app.soap.exception.ClientServiceException;

;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<ClientServiceException> {

    @Override
    public Response toResponse(ClientServiceException exception) {
        return Response.status(exception.getErrorDto().getStatus()).entity(exception.getErrorDto()).build();
    }
}
