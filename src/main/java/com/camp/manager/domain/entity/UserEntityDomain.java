package com.camp.manager.domain.entity;

public record UserEntityDomain(
        String username,
        String login,
        String password,
        String role) {
}
