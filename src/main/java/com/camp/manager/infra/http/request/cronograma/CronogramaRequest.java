package com.camp.manager.infra.http.request.cronograma;

import com.camp.manager.infra.http.request.atividade.AtividadeRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record CronogramaRequest(
        @NotNull(message = "A data inicial do acampamento não pode ser nulo")
        String dataInicial,

        @NotNull(message = "A data final do acampamento não pode ser nulo")
        String dataFinal,

        @NotNull(message = "A descrição do acampamento não pode ser nulo")
        String descricao,

        List<AtividadeRequest> atividades) {
}
