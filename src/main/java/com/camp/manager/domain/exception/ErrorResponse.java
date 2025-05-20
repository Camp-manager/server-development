package com.camp.manager.domain.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class ErrorResponse {
    private final String status;
    private final String message;
    private final String path;
    private StackTraceElement stackTrace;
}
