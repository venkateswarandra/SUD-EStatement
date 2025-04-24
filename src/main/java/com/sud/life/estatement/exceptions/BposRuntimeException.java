package com.sud.life.estatement.exceptions;


public class BposRuntimeException extends RuntimeException {
    public BposRuntimeException(Exception exception) {
        super(exception.getMessage());
    }
}
