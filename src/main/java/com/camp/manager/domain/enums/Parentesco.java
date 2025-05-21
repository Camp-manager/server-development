package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
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

    public static Parentesco fromDescricao(String descricao) {
        for (Parentesco tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }
}
