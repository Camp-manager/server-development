package com.camp.manager.domain.entity;

public record EstoqueEntityDomain(
        Long id,
        String localEstoque,
        Long quantidade,
        Long limite) {
}
