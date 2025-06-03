package com.camp.manager.infra.http.request.equipe;

import com.camp.manager.infra.http.request.cronograma.CriarCronogramaRequest;
import jakarta.validation.constraints.NotNull;

public record EquipeRequest(
        @NotNull(message = "O nome da equipe não pode ser nulo")
        String nome,

        @NotNull(message = "O tipo da equipe não pode ser nulo")
        String tipoEquipe,

        @NotNull(message = "O cronograma da equipe não pode ser nulo")
        CriarCronogramaRequest cronograma) {
}
