package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.infra.mapper.FuncionarioMapper;
import com.camp.manager.infra.persistence.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FuncionarioGatewayImpl implements FuncionarioGateway {

    private final FuncionarioRepository funcionarioRepository;
    private final FuncionarioMapper funcionarioMapper;

    @Autowired
    public FuncionarioGatewayImpl(FuncionarioRepository funcionarioRepository,
                                  FuncionarioMapper funcionarioMapper) {
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
                .collect(Collectors.toList());
    }

    @Override
    public List<FuncionarioEntityDomain> buscarTodosOsFuncionariosComBaseNoCodigoRegistro(String codigoRegistro) {
        return this.funcionarioRepository
                .findAllByCodigoRegistroOrderByNome(codigoRegistro)
                .stream()
                .map(funcionarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public FuncionarioEntityDomain buscarFuncionarioPorId(Long id) {
        return this.funcionarioRepository
                .findById(id)
                .map(funcionarioMapper::toDomain)
                .orElse(null);
    }

    @Override
    public FuncionarioEntityDomain buscarFuncionarioPorCpf(String input) {
        return this.funcionarioMapper.toDomain(this.funcionarioRepository.findByCpf(input));
    }

    @Override
    public FuncionarioEntityDomain buscarFuncionarioNoAcampamentoPorCpf(Long idAcampamento, String cpf) {
        return this.funcionarioMapper.toDomain(this.funcionarioRepository.findByEquipe_Acampamento_IdAndCpf(idAcampamento, cpf));
    }

    @Override
    public List<FuncionarioEntityDomain> buscarFuncionariosCompradoresCamisa(String codigoRegistro) {
        return this.funcionarioRepository.findAllByCodigoRegistroAndCamisetaIsNotNull(codigoRegistro)
                .stream()
                .map(funcionarioMapper::toDomain)
                .collect(Collectors.toList());
    }
}
