package edu.mum.se.poseidon.core.controllers;

import org.springframework.http.HttpStatus;

public class PoseidonException extends Exception {

    final static String FAIL_MESSAGE = "Failed to execute a request.";
    private final HttpStatus httpStatus;

    public PoseidonException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }

    HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
