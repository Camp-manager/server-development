package com.camp.manager.domain.exception.custom;

public class LocalDateConverterException extends RuntimeException {
    public LocalDateConverterException(String message) {
        super(message);
    }

    public LocalDateConverterException() {
        super("Erro ao converter data informada!");
    }
}
