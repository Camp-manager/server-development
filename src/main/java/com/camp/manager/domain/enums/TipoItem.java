package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
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

    public static TipoItem fromDescricao(String descricao) {
        for (TipoItem tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }
}
