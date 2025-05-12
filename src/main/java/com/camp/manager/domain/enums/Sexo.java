package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo {
    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino");

    private final String valor;
    private final String descricao;
}
