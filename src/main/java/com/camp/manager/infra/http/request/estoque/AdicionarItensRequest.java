package com.camp.manager.infra.http.request.estoque;

import lombok.Getter;

import java.util.List;

@Getter
public class AdicionarItensRequest {
    private Long idEstoque;
    private List<ItemRequest> itens;
}
