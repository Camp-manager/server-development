package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.FuncionarioEntityDomain;

import java.util.List;

public interface FuncionarioGateway {
    boolean funcionarioEhExistentePorCpf(String cpf);
    void inserirFuncionario(FuncionarioEntityDomain funcionarioDomain);
    List<FuncionarioEntityDomain> buscarTodosOsFuncionariosComBaseNaEquipe(Long idEquipe);
}
