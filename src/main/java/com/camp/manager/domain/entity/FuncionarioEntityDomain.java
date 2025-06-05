package com.camp.manager.domain.entity;

public record FuncionarioEntityDomain(
        Long id,
        String nome,
        String cpf,
        String telefone,
        String email,
        String codigoRegistro,
        String habilidade,
        CamisetaEntityDomain camiseta,
        EquipeEntityDomain equipe,
        CarteirinhaEntityDomain carteirinha){
}
