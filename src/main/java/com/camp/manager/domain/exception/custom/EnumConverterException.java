package com.camp.manager.domain.exception.custom;

public class EnumConverterException extends RuntimeException {
    public EnumConverterException(String message) {
        super(message);
    }

    public EnumConverterException() {
        super("Erro ao converter enum.");
    }
}
