package com.camp.manager.application;

import jakarta.validation.constraints.NotBlank;

public record LoginUserRequest(
        @NotBlank(message = "Login de Usuário não pode ser nulo ou em branco!") String login,
        @NotBlank(message = "Senha do Usuário não pode ser nulo ou em branco!") String password) {
}
