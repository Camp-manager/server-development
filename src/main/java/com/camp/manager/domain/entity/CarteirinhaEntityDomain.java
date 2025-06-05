package com.camp.manager.domain.entity;

public record CarteirinhaEntityDomain(
        Long id,
        String textoApresentacao,
        TemaEntityDomain tema) {
}
