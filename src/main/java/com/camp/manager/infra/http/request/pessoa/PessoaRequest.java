package com.camp.manager.infra.http.request.pessoa;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaRequest(
        @NotBlank(message = "Nome Completo não pode ser nulo ou em branco!")
        String nomeCompleto,

        @CPF(message = "CPF inválido!")
        @NotBlank(message = "CPF não pode ser nulo ou em branco!")
        String cpf,

        @NotBlank(message = "Data de Nascimento não pode ser nulo ou em branco!")
        String dataNascimento


) {
}
