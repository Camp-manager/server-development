package com.camp.manager.infra.http.request.tema;

public record AtualizarTemaRequest(
        @Non
        Long id,
        String descricao,
        Double precoCamiseta) {
}
