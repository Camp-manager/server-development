package com.camp.manager.domain.entity;

public record EnderecoEntityDomain(
        Long id,
        String cep,
        String rua,
        Long numero,
        String cidade,
        String bairro,
        String pontoReferencia
) {
}
