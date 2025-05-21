package com.camp.manager.domain.exception.custom;

public class FileTypeException extends RuntimeException {
    public FileTypeException(String message) {
        super(message);
    }

    public FileTypeException() {
        super("Tipo de arquivo inválido!");
    }
}
