package com.camp.manager.domain.enums;

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
}
