package com.camp.manager.infra.http.dto.estoque;

import com.camp.manager.domain.entity.EstoqueEntityDomain;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class EstoqueDTO {
    private final Long id;
    private final String localEstoque;
    private final Long quantidade;
    private final Long limite;
    private final List<ItemDTO> itens;

    public EstoqueDTO(EstoqueEntityDomain estoqueEntityDomain) {
        this.id = estoqueEntityDomain.id();
        this.localEstoque = estoqueEntityDomain.localEstoque();
        this.quantidade = estoqueEntityDomain.quantidade();
        this.limite = estoqueEntityDomain.limite();
        this.itens = estoqueEntityDomain.itens() != null ? ItemDTO.converter(estoqueEntityDomain.itens()) : new ArrayList<>();
    }

    public static List<EstoqueDTO> converter(List<EstoqueEntityDomain> estoqueEntityDomains) {
        return estoqueEntityDomains.stream()
                .map(EstoqueDTO::new)
                .toList();
    }
}
