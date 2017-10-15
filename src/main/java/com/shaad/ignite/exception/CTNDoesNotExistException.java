package com.shaad.ignite.exception;

public class CTNDoesNotExistException extends RuntimeException {
    public CTNDoesNotExistException(long ctn) {
        super(String.format("Ctn with id %d does not exist", ctn));
    }
}
