package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LocalEstoque {
    ENFERMARIA("E", "Enfermaria"),
    CAPELA("C", "Capela"),
    COZINHA("Z", "Cozinha"),
    BAR("B", "Bar"),
    LIVRARIA("L", "Livraria"),
    SECRETARIA("S", "Secretaria"),
    MANUTENCAO("M", "Manutenção");

    private final String valor;
    private final String descricao;

    public static LocalEstoque fromDescricao(String descricao) {
        for (LocalEstoque tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }

    public static LocalEstoque fromValor(String valor) {
        for (LocalEstoque tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Valor inválida: " + valor);
    }
}
