package com.camp.manager.domain.exception.custom;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
        super(message);
    }

    public NotFoundException() {
        super("Entidade não encontrada!");
    }
}
