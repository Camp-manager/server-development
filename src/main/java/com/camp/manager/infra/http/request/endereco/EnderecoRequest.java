package com.camp.manager.infra.http.request.endereco;

import jakarta.validation.constraints.NotBlank;

public record EnderecoRequest(
        @NotBlank(message = "CEP não pode ser nulo ou em branco!")
        String cep,

        @NotBlank(message = "Rua não pode ser nulo ou em branco!")
        String rua,

        @NotBlank(message = "Número não pode ser nulo ou em branco!")
        String numero,

        @NotBlank(message = "Cidade não pode ser nulo ou em branco!")
        String cidade,

        @NotBlank(message = "Bairro não pode ser nulo ou em branco!")
        String bairro,

        String pontoReferencia
) {
}
