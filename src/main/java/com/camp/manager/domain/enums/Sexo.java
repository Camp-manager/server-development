package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Sexo {
    MASCULINO("M", "Masculino"),
    FEMININO("F", "Feminino");

    private final String valor;
    private final String descricao;

    public static Sexo fromDescricao(String descricao) {
        for (Sexo tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }
}
