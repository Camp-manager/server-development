package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Resposta {

    SIM("S", "Sim"),
    NAO("N", "Não");

    private final String valor;
    private final String descricao;
}
