package com.camp.manager.domain.entity;

public record ItemEntityDomain(
        Long id,
        String descricao,
        Long quantidade,
        String tipoItem,
        String validade,
        Double valor) {
}
