package com.camp.manager.domain.entity;

import java.time.LocalDate;

public record CronogramaEntityDomain(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFinal,
        String descricao) {
}
