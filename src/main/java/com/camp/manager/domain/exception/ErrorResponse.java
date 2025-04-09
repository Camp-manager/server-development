package com.camp.manager.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Locale;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private final HttpStatus status;
    private final String message;
    private final String path;
    private StackTraceElement stackTrace;
}
