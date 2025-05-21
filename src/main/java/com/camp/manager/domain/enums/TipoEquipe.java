package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoEquipe {
    TRABALHO("T","Trabalho"),
    CAMPISTA("C","Campista");

    private final String valor;
    private final String descricao;

    public static TipoEquipe fromDescricao(String descricao) {
        for (TipoEquipe tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }
}
