package com.camp.manager.domain.entity;

import java.util.List;

public record EstoqueEntityDomain(
        Long id,
        String localEstoque,
        Long quantidade,
        Long limite,
        List<ItemEntityDomain> itens) {
}
