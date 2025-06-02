package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CategoriaTipoAcampamento {
    ADULTO("A", "Adulto"),
    JOVEM("J", "Jovem"),
    CRIANCA("C", "Criança");

    private final String valor;
    private final String descricao;

    public static CategoriaTipoAcampamento fromDescricao(String descricao) {
        for (CategoriaTipoAcampamento tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }

    public static CategoriaTipoAcampamento fromValor(String valor) {
        for (CategoriaTipoAcampamento tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Valor inválido: " + valor);
    }
}
