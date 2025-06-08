package com.camp.manager.domain.exception.custom;

public class LocalDateInvalidException extends RuntimeException {
    public LocalDateInvalidException(String message) {
        super(message);
    }
}
