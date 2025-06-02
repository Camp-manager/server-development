package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
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

    public static TipoDoacao fromDescricao(String descricao) {
        for (TipoDoacao tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }

    public static TipoDoacao fromValor(String valor) {
        for (TipoDoacao tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Valor inválida: " + valor);
    }
}
