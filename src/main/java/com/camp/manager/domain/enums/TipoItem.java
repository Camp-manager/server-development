package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoItem {

    TIPOITEM("","");

    private final String valor;
    private final String descricao;
}
