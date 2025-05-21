package com.camp.manager.domain.entity;

import java.math.BigDecimal;

public record TemaEntityDomain(
        Long id,
        String descricao,
        byte[] imagemDesign,
        BigDecimal precoCamiseta,
        BigDecimal precoAcampamento) {
}
