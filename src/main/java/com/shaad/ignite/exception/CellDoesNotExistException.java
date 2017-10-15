package com.shaad.ignite.exception;

public class CellDoesNotExistException extends RuntimeException {
    public CellDoesNotExistException(long cellId) {
        super(String.format("Cell with id %d does not exist", cellId));
    }
}
