package com.camp.manager.infra.exception;

import com.camp.manager.domain.exception.ErrorResponse;
import com.camp.manager.domain.exception.custom.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j(topic = "Camp_manager_exception")
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @Value("${spring.error.include-stacktrace}")
    private boolean serverIncludeStackTrace;

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("MethodArgumentNotValidException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                status.toString(),
                "Argumentos fornecidos inválidos",
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @Override
    @ResponseStatus(HttpStatus.NOT_FOUND)
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("MissingPathVariableException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                status.toString(),
                "Variavel de caminho não encontrada",
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @Override
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.error("HttpRequestMethodNotSupportedException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                status.toString(),
                "Método não suportado",
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    private ResponseEntity<Object> handleAllUncaughtExceptions(Exception ex, WebRequest request) {
        log.error("Exception: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                "Erro interno do servidor",
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }


    // ----------------- Exceções de Negócio ----------------- //

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(UserFoundException.class)
    private ResponseEntity<Object> userFoundExceptionHandler(UserFoundException ex, WebRequest request) {
        log.error("UserFoundException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    private ResponseEntity<Object> notFoundExceptionHandler(NotFoundException ex, WebRequest request) {
        log.error("NotFoundException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(PasswordInvalidException.class)
    private ResponseEntity<Object> passwordInvalidExceptionHandler(PasswordInvalidException ex, WebRequest request) {
        log.error("PasswordInvalidException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.UNAUTHORIZED.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(LocalDateConverterException.class)
    private ResponseEntity<Object> localDateConverterExceptionHandler(LocalDateConverterException ex, WebRequest request) {
        log.error("LocalDateConverterException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(FileTypeException.class)
    private ResponseEntity<Object> fileTypeExceptionHandler(FileTypeException ex, WebRequest request) {
        log.error("FileTypeException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(FileProcessingException.class)
    private ResponseEntity<Object> fileProcessingExceptionHandler(FileProcessingException ex, WebRequest request) {
        log.error("FileProcessingException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EnumConverterException.class)
    private ResponseEntity<Object> enumConverterExceptionHandler(EnumConverterException ex, WebRequest request) {
        log.error("EnumConverterException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler(EntityFoundException.class)
    private ResponseEntity<Object> entityFoundExceptionHandler(EntityFoundException ex, WebRequest request) {
        log.error("EntityFoundException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.CONFLICT.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(LimitOverflowException.class)
    private ResponseEntity<Object> limitOverflowExceptionHandler(LimitOverflowException ex, WebRequest request) {
        log.error("LimitOverflowException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(LocalDateInvalidException.class)
    private ResponseEntity<Object> localDateInvalidExceptionHandler(LocalDateInvalidException ex, WebRequest request) {
        log.error("LocalDateInvalidException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }


    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(NotAcceptableException.class)
    private ResponseEntity<Object> notAcceptableExceptionHandler(NotAcceptableException ex, WebRequest request) {
        log.error("NotAcceptableException: {}", ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_ACCEPTABLE.name(),
                ex.getMessage(),
                request.getDescription(false)
        );
        if (serverIncludeStackTrace) {
            errorResponse.setStackTrace(ex.getStackTrace()[0]);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(errorResponse);
    }
}
