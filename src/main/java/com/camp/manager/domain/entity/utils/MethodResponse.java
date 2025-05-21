package com.camp.manager.domain.entity.utils;


public record MethodResponse<T>(Long status, String message, T data) {
}
