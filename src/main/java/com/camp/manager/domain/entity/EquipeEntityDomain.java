package com.camp.manager.domain.entity;

public record EquipeEntityDomain(
        Long id,
        String nome,
        String tipoEquipe,
        CronogramaEntityDomain cronograma,
        AcampamentoEntityDomain acampamento) {
}
