package com.camp.manager.domain.exception.custom;

public class LimitOverflowException extends RuntimeException {
    public LimitOverflowException(String message) {
        super(message);
    }

    public LimitOverflowException() {
        super("Limite do Acampamento alcançado!");
    }
}
