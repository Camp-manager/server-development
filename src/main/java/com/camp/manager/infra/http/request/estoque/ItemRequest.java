package com.camp.manager.infra.http.request.estoque;

import lombok.Getter;

@Getter
public class ItemRequest {
    private String descricao;
    private Long quantidade;
    private String tipoItem;
    private String validade;
    private Double valor;
}
