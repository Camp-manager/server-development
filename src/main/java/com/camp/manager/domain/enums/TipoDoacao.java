package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoDoacao {
    ALIMENTO("A", "Alimento"),
    ROUPA("R", "Roupa"),
    DINHERO("D", "Dinheiro"),
    MOVEL("M", "Móvel"),
    OUTROS("O", "Outros");


    private final String valor;
    private final String descricao;
}
