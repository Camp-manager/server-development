package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.ItemEntityDomain;
import com.camp.manager.domain.enums.TipoItem;
import com.camp.manager.infra.persistence.entity.ItemEntityJpa;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper implements Mapper<ItemEntityJpa, ItemEntityDomain> {

    @Override
    public ItemEntityDomain toDomain(ItemEntityJpa itemEntityJpa) {
        return new ItemEntityDomain(
                itemEntityJpa.getId(),
                itemEntityJpa.getDescricao(),
                itemEntityJpa.getQuantidade(),
                itemEntityJpa.getTipoItem().getDescricao(),
                itemEntityJpa.getValidade(),
                itemEntityJpa.getValor()
        );
    }

    @Override
    public ItemEntityJpa toEntity(ItemEntityDomain itemEntityDomain) {
        return new ItemEntityJpa(
                itemEntityDomain.id(),
                itemEntityDomain.descricao(),
                itemEntityDomain.quantidade(),
                TipoItem.fromDescricao(itemEntityDomain.tipoItem()),
                itemEntityDomain.validade(),
                itemEntityDomain.valor()
        );
    }
}
