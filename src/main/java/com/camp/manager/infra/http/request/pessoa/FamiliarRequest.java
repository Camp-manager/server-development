package com.camp.manager.infra.http.request.pessoa;

import com.camp.manager.infra.http.request.endereco.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;

public record FamiliarRequest(
        @NotBlank(message = "Nome não pode ser nulo ou em branco!")
        String nome,

        @NotBlank(message = "Parentesco não pode ser nulo ou em branco!")
        String parentesco,

        @NotBlank(message = "Telefone não pode ser nulo ou em branco!")
        String telefone,

        EnderecoRequest endereco
) {
}
