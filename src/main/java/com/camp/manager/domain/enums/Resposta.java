package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Resposta {
    SIM("S", "Sim"),
    NAO("N", "Não");

    private final String valor;
    private final String descricao;

    public static Resposta fromDescricao(String descricao) {
        for (Resposta tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }

    public static Resposta fromValor(String valor) {
        for (Resposta tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Valor inválida: " + valor);
    }

    public static Resposta fromBoolean(Boolean aBoolean) {
        if (aBoolean == null) {
            return null;
        }
        return aBoolean ? SIM : NAO;
    }
}
