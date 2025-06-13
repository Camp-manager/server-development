package com.camp.manager.domain.entity;

public record EquipeDiaFuncaoEntityDomain(
        Long id,
        String funcao,
        String data,
        EquipeEntityDomain equipe) {
}
