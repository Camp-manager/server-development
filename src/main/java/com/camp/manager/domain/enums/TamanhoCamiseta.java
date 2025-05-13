package com.camp.manager.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TamanhoCamiseta {

    PP("PP","Extra Pequeno"),
    P("P","Pequeno"),
    M("M","Médio"),
    G("G","Grande"),
    GG("GG","Extra Grande"),
    G1("G1","Extra Grande 1"),
    G2("G2","Extra Grande 2"),
    G3("G3","Extra Grande 3"),
    G4("G4","Extra Grande 4"),
    G5("G5","Extra Grande 5"),
    G6("G6","Extra Grande 6"),
    G7("G7","Extra Grande 7");

    private final String valor;
    private final String descricao;
}
