package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.EstoqueEntityDomain;
import com.camp.manager.domain.entity.ItemEntityDomain;
import com.camp.manager.domain.enums.LocalEstoque;
import com.camp.manager.infra.persistence.entity.EstoqueEntityJpa;
import com.camp.manager.infra.persistence.entity.ItemEntityJpa;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class EstoqueMapper implements Mapper<EstoqueEntityJpa, EstoqueEntityDomain> {

    private final ItemMapper itemMapper;

    public EstoqueMapper(@Lazy ItemMapper itemMapper) {
        this.itemMapper = itemMapper;
    }

    @Override
    public EstoqueEntityDomain toDomain(EstoqueEntityJpa estoqueEntityJpa) {
        return new EstoqueEntityDomain(
                estoqueEntityJpa.getId(),
                estoqueEntityJpa.getLocalEstoque().getDescricao(),
                estoqueEntityJpa.getQuantidade(),
                estoqueEntityJpa.getLimite(),
                estoqueEntityJpa.getItens() != null ? this.converterParaDominio(estoqueEntityJpa.getItens()) : new ArrayList<>()
        );
    }

    @Override
    public EstoqueEntityJpa toEntity(EstoqueEntityDomain estoqueEntityDomain) {
        return new EstoqueEntityJpa(
                estoqueEntityDomain.id(),
                estoqueEntityDomain.localEstoque() != null ? LocalEstoque.fromDescricao(estoqueEntityDomain.localEstoque()) : null,
                estoqueEntityDomain.quantidade(),
                estoqueEntityDomain.limite(),
                estoqueEntityDomain.itens() != null ? this.converterParaEntidade(estoqueEntityDomain.itens()) : new ArrayList<>()
        );
    }

    public List<ItemEntityDomain> converterParaDominio(List<ItemEntityJpa> itemEntityJpas) {
        return itemEntityJpas.stream()
                .map(itemMapper::toDomain)
                .collect(Collectors.toList());
    }

    public List<ItemEntityJpa> converterParaEntidade(List<ItemEntityDomain> itemEntityDomains) {
        return itemEntityDomains.stream()
                .map(itemMapper::toEntity)
                .collect(Collectors.toList());
    }
}
