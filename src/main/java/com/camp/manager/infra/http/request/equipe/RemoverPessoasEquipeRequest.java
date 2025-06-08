package com.camp.manager.infra.http.request.equipe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record RemoverPessoasEquipeRequest(

        @NotNull(message = "O id da equipe não pode ser nulo.")
        @Positive(message = "O id da equipe deve ser um número positivo.")
        Long idEquipe,

        List<@Positive(message = "O id das pessoas deve ser um número positivo.") Long> idsPessoas
) {
}
