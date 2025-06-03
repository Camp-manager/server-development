package com.camp.manager.domain.entity;

public record FamiliarEntityDomain(
        Long id,
        String nome,
        String parentesco,
        String telefone,
        EnderecoEntityDomain endereco
) {
}
