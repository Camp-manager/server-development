package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.EstoqueEntityDomain;

import java.util.List;

public interface EstoqueGateway {
    List<EstoqueEntityDomain> buscarTodosOsEstoques();
    EstoqueEntityDomain buscarEstoquePorId(Long idEstoque);
    boolean estoqueEhExistente(Long idEstoque);
    void salvarEstoque(EstoqueEntityDomain estoqueEntityDomain);
}
