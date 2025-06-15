package com.camp.manager.domain.entity;

import java.time.LocalDate;
import java.util.List;

public record CronogramaEquipeEntityDomain(
        Long id,
        LocalDate dataInicio,
        LocalDate dataFinal,
        String descricao,
        EquipeEntityDomain equipe,
        List<AtividadeEntityDomain> atividades) {
}
