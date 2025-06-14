package com.camp.manager.infra.http.request.user;

import jakarta.validation.constraints.NotBlank;

public record CriarUsuarioRequest(
        @NotBlank(message = "Nome do Usuário não pode ser nulo ou em branco!")
        String username,

        @NotBlank(message = "Login de Usuário não pode ser nulo ou em branco!")
        String login,

        @NotBlank(message = "Senha do Usuário não pode ser nulo ou em branco!")
        String password,

        @NotBlank(message = "Role do Usuário não pode ser nulo ou em branco!")
        String roleUser) {
}
