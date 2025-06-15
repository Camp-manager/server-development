package com.camp.manager.infra.http.request.equipe;

public record LiderEquipeRequest(
        Long idEquipe,
        Long idFuncionario,
        Long idAcampamento
) {
}
