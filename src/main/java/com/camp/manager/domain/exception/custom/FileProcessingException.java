package com.camp.manager.domain.exception.custom;

public class FileProcessingException extends RuntimeException {
    public FileProcessingException(String message) {
        super(message);
    }

    public FileProcessingException() {
        super("Erro ao processar o arquivo!");
    }
}
