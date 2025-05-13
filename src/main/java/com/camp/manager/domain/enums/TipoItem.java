package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoItem {
    MEDICAMENTO("M","Medicamento"),
    ALIMENTO("A","Alimento"),
    VESTIMENTA("V","Vestimenta"),
    LIVRO("L","Livro"),
    BEBIDA("B","Bebida"),
    OUTROS("O","Outros");

    private final String valor;
    private final String descricao;
}
