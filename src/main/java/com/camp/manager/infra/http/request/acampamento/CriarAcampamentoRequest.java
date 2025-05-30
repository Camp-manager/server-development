package com.camp.manager.infra.http.request.acampamento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record CriarAcampamentoRequest(
        @NotNull(message = "A data inicial do acampamento não pode ser nulo")
        String dataInicial,

        @NotNull(message = "A data final do acampamento não pode ser nulo")
        String dataFinal,

        @NotNull(message = "A descrição do acampamento não pode ser nulo")
        String descricao,

        @NotNull(message = "O nome do acampamento não pode ser nulo")
        String nomeDoAcampamento,

        @NotNull(message = "O limite de campistas não pode ser nulo")
        @Positive(message = "O limite de campistas deve ser maior ou igual a zero")
        Long limiteCampistas,

        @NotNull(message = "O limite de funcionários não pode ser nulo")
        @Positive(message = "O limite de funcionários deve ser maior ou igual a zero")
        Long limiteFuncionario,

        @NotNull(message = "O id do tema não pode ser nulo")
        @Positive(message = "O id do tema deve ser maior ou igual a zero")
        Long idTema,

        @NotNull(message = "O id do tipo de acampamento não pode ser nulo")
        @Positive(message = "O id do tipo de acampamento deve ser maior ou igual a zero")
        Long idTipoAcampamento) {
}
