package com.camp.manager.domain.entity;

import lombok.Getter;


public record MethodResponse<T>(Long status, String message, T data) {
}
