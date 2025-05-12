package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEquipe {
    TRABALHO("T","Trabalho"),
    CAMPISTA("C","Campista");

    private final String valor;
    private final String descricao;
}
