package com.camp.manager.infra.http.request.equipe;

import com.camp.manager.infra.http.request.cronograma.CronogramaRequest;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record EquipeRequest(
        @NotNull(message = "O nome da equipe não pode ser nulo")
        String nome,

        @NotNull(message = "O tipo da equipe não pode ser nulo")
        String tipoEquipe,

        @NotNull(message = "O cronogramas da equipe não pode ser nulo")
        List<CronogramaRequest> cronogramas) {
}
