package com.sud.life.estatement.exceptions;

import lombok.Getter;

@Getter
public class BposBusinessException extends RuntimeException {

    private String messageCode;
    private String message;

    public BposBusinessException(String messageCode) {
        super(messageCode);
        this.messageCode = messageCode;
        this.message = null;
    }

    public BposBusinessException(String messageCode, String message) {
        super(message);
        this.messageCode = messageCode;
        this.message = message;
    }
}