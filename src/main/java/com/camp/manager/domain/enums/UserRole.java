package com.camp.manager.domain.enums;

import lombok.Getter;

@Getter
public enum UserRole {
    ADMIN("administrador") ,
    USER("usuario");

    private final String role;

    UserRole(String role) {
        this.role = role;
    }
}
