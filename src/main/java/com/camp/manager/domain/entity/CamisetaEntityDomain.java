package com.camp.manager.domain.entity;

public record CamisetaEntityDomain(
        Long id,
        String tamanhoCamiseta,
        TemaEntityDomain tema) {
}
