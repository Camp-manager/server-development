package com.camp.manager.infra.http.request.tema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AtualizarTemaRequest(
        @NotNull(message = "Id do tema não pode ser nulo!")
        Long id,
        @NotBlank(message = "Descrição do tema não pode ser nulo ou em branco!")
        String descricao,

        Double precoCamiseta) {
}
