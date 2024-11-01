package com.tpIntegrador.tpCozzani.exceptions;

import org.springframework.http.HttpStatus;




public class ExceptionsSeguridad extends RuntimeException {
    private final HttpStatus status;
    public ExceptionsSeguridad(final HttpStatus httpStatus) {

        this.status = httpStatus;

    }

    public ExceptionsSeguridad(final HttpStatus httpStatus, final String message) {
        super(message);
        this.status = httpStatus;
    }
}