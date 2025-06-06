package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.FuncionarioEntityDomain;

import java.util.List;

public interface FuncionarioGateway {
    boolean funcionarioEhExistentePorCpf(String cpf);
    boolean funcionarioEhExistentePorId(Long id);
    void inserirFuncionario(FuncionarioEntityDomain funcionarioDomain);
    List<FuncionarioEntityDomain> buscarTodosOsFuncionariosComBaseNaEquipe(Long idEquipe);
    List<FuncionarioEntityDomain> buscarTodosOsFuncionariosComBaseNoCodigoRegistro(String codigoRegistro);
    FuncionarioEntityDomain buscarFuncionarioPorId(Long id);
}
