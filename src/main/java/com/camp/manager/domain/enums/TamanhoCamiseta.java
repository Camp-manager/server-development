package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TamanhoCamiseta {

    P("",""),
    M("",""),
    G("",""),
    GG("","");

    private final String valor;
    private final String descricao;
}
