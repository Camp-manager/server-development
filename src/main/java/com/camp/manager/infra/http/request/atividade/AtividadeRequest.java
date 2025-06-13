package com.camp.manager.infra.http.request.atividade;

public record AtividadeRequest(
        String tipo,
        String horario,
        String descricao) {
}
