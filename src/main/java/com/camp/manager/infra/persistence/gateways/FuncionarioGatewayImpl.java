package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.entity.FuncionarioEntityJpa;
import com.camp.manager.infra.persistence.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuncionarioGatewayImpl implements FuncionarioGateway {

    private final FuncionarioRepository funcionarioRepository;
    private final Mapper<FuncionarioEntityJpa, FuncionarioEntityDomain> funcionarioMapper;

    @Autowired
    public FuncionarioGatewayImpl(FuncionarioRepository funcionarioRepository,
                                  Mapper<FuncionarioEntityJpa, FuncionarioEntityDomain> funcionarioMapper) {
        this.funcionarioRepository = funcionarioRepository;
        this.funcionarioMapper = funcionarioMapper;
    }

    @Override
    public boolean funcionarioEhExistentePorCpf(String cpf) {
        return this.funcionarioRepository.existsByCpf(cpf);
    }

    @Override
    public boolean funcionarioEhExistentePorId(Long id) {
        return this.funcionarioRepository.existsById(id);
    }

    @Override
    public void inserirFuncionario(FuncionarioEntityDomain funcionarioDomain) {
        this.funcionarioRepository.save(this.funcionarioMapper.toEntity(funcionarioDomain));
    }

    @Override
    public List<FuncionarioEntityDomain> buscarTodosOsFuncionariosComBaseNaEquipe(Long idEquipe) {
        return this.funcionarioRepository
                .findAllByEquipe_IdOrderByNome(idEquipe)
                .stream()
                .map(funcionarioMapper::toDomain)
                .toList();
    }

    @Override
    public List<FuncionarioEntityDomain> buscarTodosOsFuncionariosComBaseNoCodigoRegistro(String codigoRegistro) {
        return this.funcionarioRepository
                .findAllByCodigoRegistroOrderByNome(codigoRegistro)
                .stream()
                .map(funcionarioMapper::toDomain)
                .toList();
    }

    @Override
    public FuncionarioEntityDomain buscarFuncionarioPorId(Long id) {
        return this.funcionarioRepository
                .findById(id)
                .map(funcionarioMapper::toDomain)
                .orElse(null);
    }
}
