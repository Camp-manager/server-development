package com.camp.manager.infra.http.request.cronograma;

import jakarta.validation.constraints.NotNull;

public record AlterarCronogramaRequest(
        @NotNull(message = "O ID do acampamento não pode ser nulo")
        Long id,

        @NotNull(message = "A data inicial do acampamento não pode ser nulo")
        String dataInicial,

        @NotNull(message = "A data final do acampamento não pode ser nulo")
        String dataFinal,

        @NotNull(message = "A descrição do acampamento não pode ser nulo")
        String descricao
) {
}
