package com.camp.manager.infra.http.request.user;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenhaUsuarioRequest(
        @NotBlank(message = "Login de Usuário não pode ser nulo ou em branco!")
        String login,

        @NotBlank(message = "Senha atual não pode ser nulo ou em branco!")
        String senhaAtual,

        @NotBlank(message = "Nova senha não pode ser nulo ou em branco!")
        String novaSenha) {
}
