package com.camp.manager.infra.http.dto.estoque;

import com.camp.manager.domain.entity.ItemEntityDomain;
import lombok.Getter;

import java.util.List;

@Getter
public class ItemDTO {
    private final Long id;
    private final String descricao;
    private final Long quantidade;
    private final String tipoItem;
    private final String validade;
    private final Double valor;

    public ItemDTO(ItemEntityDomain itemEntityDomain) {
        this.id = itemEntityDomain.id();
        this.descricao = itemEntityDomain.descricao();
        this.quantidade = itemEntityDomain.quantidade();
        this.tipoItem = itemEntityDomain.tipoItem();
        this.validade = itemEntityDomain.validade();
        this.valor = itemEntityDomain.valor();
    }

    public static List<ItemDTO> converter(List<ItemEntityDomain> itemEntityDomains) {
        return itemEntityDomains.stream()
                .map(ItemDTO::new)
                .toList();
    }
}
