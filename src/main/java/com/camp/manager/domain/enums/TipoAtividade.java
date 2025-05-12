package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAtividade {
    ATIVIDADE("ATIVIDADE", "Atividade");

    private final String valor;
    private final String descricao;
}
