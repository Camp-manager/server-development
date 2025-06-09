package com.camp.manager.infra.http.request.tema;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CriarTemaRequest(
        @NotBlank(message = "Descrição do tema não pode ser nulo ou em branco!")
        String descricao,

        @NotNull(message = "Preço da camiseta não pode ser nulo!")
        @Positive(message = "O preço da camiseta deve ser maior que zero")
        Double precoCamiseta,

        @NotNull(message = "Preço do acampamento não pode ser nulo!")
        @Positive(message = "O preço do acampamento deve ser maior que zero")
        Double precoAcampamento) {
}
