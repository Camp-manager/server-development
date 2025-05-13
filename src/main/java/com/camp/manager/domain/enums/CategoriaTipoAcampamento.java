package com.camp.manager.domain.enums;

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
}
