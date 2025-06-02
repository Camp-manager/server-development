package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoAtividade {
    DEVERES("D", "Deveres"),
    REUNIAO("R", "Reunião");

    private final String valor;
    private final String descricao;

    public static TipoAtividade fromDescricao(String descricao) {
        for (TipoAtividade tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }

    public static TipoAtividade fromValor(String valor) {
        for (TipoAtividade tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Valor inválida: " + valor);
    }

}
