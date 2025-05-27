package com.camp.manager.infra.http.request.tema;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record AtualizarTemaRequest(
        @NotNull(message = "Id do tema não pode ser nulo!")
        @Positive(message = "O id do tema deve ser maior que zero")
        Long id,

        @NotBlank(message = "Descrição do tema não pode ser nulo ou em branco!")
        String descricao,

        @Positive(message = "O preço da camiseta deve ser maior que zero")
        Double precoCamiseta) {
}
