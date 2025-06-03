package com.camp.manager.infra.http.request.pessoa;

import jakarta.validation.constraints.NotBlank;

public record CriarFuncionarioRequest(

        @NotBlank(message = "Nome não pode ser nulo ou em branco!")
        String nome,

        @NotBlank(message = "CPF não pode ser nulo ou em branco!")
        String cpf,

        @NotBlank(message = "Telefone não pode ser nulo ou em branco!")
        String telefone,

        @NotBlank(message = "Habilidade não pode ser nulo ou em branco!")
        String habilidade) {
}
