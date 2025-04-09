package com.camp.manager.domain.exception.custom;

public class UserFoundException extends RuntimeException {
    public UserFoundException(String message) {
        super(message);
    }

    public UserFoundException() {
        super("Usuário já existe!");
    }
}
