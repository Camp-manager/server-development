package com.camp.manager.domain.exception.custom;

public class EntityFoundException extends RuntimeException {
    public EntityFoundException(String message) {
        super(message);
    }
}
