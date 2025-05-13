package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Parentesco {
    PAI("PA", "Pai"),
    MAE("MA", "Mãe"),
    RESPONSAVEL("RE", "Responsável"),
    OUTRO("OU", "Outro");

    private final String valor;
    private final String descricao;
}
