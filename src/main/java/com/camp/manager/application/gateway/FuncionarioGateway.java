package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.FuncionarioEntityDomain;

public interface FuncionarioGateway {
    boolean funcionarioEhExistentePorCpf(String cpf);
    void inserirFuncionario(FuncionarioEntityDomain funcionarioDomain);
}
