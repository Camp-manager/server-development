package com.camp.manager.domain.entity;

import java.time.LocalTime;

public record AtividadeEntityDomain(
        Long id,
        String tipoAtividade,
        LocalTime horario,
        String descricao,
        CronogramaEquipeEntityDomain cronograma) {
}
