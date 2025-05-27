package com.camp.manager.domain.entity.utils;


public record MethodResponse<T>(
        int status,
        String message,
        T data) {
}
