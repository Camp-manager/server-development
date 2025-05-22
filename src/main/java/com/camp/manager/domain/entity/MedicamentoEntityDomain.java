package com.camp.manager.domain.entity;

import java.math.BigDecimal;

public record MedicamentoEntityDomain(
        Long id,
        String nome,
        String quantidade,
        String tipo,
        BigDecimal valor) {
}
