package com.camp.manager.infra.http.request.equipe;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.List;

public record EquipesRequest(
        @NotNull(message = "O id do acampamento não pode ser nulo")
        @Positive(message = "O id do acampamento deve ser maior ou igual a zero")
        Long idAcampamento,

        @NotNull(message = "A lista de equipes não pode ser nula")
        List<EquipeRequest> equipes) {
}
