package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAtividade {
    DEVERES("D", "Deveres"),
    REUNIAO("R", "Reunião");

    private final String valor;
    private final String descricao;
}
