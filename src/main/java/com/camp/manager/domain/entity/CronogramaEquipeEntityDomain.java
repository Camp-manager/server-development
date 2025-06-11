package com.camp.manager.domain.entity;

import java.time.LocalDate;

public record CronogramaEquipeEntityDomain(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFinal,
        String descricao,
        EquipeEntityDomain equipe
) {
}
