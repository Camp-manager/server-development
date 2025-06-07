package com.camp.manager.infra.http.request.equipe;

public record LiderEquipeRequest(
        Long idEquipe,
        Long idCampista,
        Long idAcampamento
) {
}
