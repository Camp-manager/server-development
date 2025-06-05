package com.camp.manager.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("ADMIN"),
    CAMPISTA("CAMPISTA"),
    FUNCIONARIO("FUNCIONARIO");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
