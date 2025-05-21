package com.camp.manager.domain.exception.custom;

public class DirectoryException extends RuntimeException {
    public DirectoryException(String message) {
        super(message);
    }

    public DirectoryException() {
        super("Diretório não foi encontrado ou apresenta problemas para ser criado.");
    }
}
