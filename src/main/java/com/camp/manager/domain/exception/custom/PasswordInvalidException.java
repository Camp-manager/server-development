package com.camp.manager.domain.exception.custom;

public class PasswordInvalidException extends RuntimeException {
    public PasswordInvalidException(String message) {
        super(message);
    }

    public PasswordInvalidException() {
        super("Senha inválida!");
    }
}
