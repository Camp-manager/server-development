package com.camp.manager.domain.enums;

import com.camp.manager.domain.exception.custom.EnumConverterException;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TipoMedicamento {
    PILULA("P", "Pilula"),
    COMPRIMIDO("C", "Comprimido"),
    SOLUCAO("S", "Solução"),
    LIQUIDO("L", "Liquido"),
    PASTA("A", "Pasta"),
    PO("O", "Pó"),
    GEL("G", "Gel"),
    OUTRO("X","Outro");

    private final String valor;
    private final String descricao;

    public static TipoMedicamento fromDescricao(String descricao) {
        for (TipoMedicamento tipo : values()) {
            if (tipo.getDescricao().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Descrição inválida: " + descricao);
    }

    public static TipoMedicamento fromValor(String valor) {
        for (TipoMedicamento tipo : values()) {
            if (tipo.valor.equalsIgnoreCase(valor)) {
                return tipo;
            }
        }
        throw new EnumConverterException("Valor inválida: " + valor);
    }
}
