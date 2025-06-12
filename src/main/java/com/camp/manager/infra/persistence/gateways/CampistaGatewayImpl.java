package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.infra.mapper.CampistaMapper;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.entity.CampistaEntityJpa;
import com.camp.manager.infra.persistence.repository.CampistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CampistaGatewayImpl implements CampistaGateway {

    private final CampistaRepository campistaRepository;
    private final CampistaMapper campistaMapper;

    @Autowired
    public CampistaGatewayImpl(CampistaRepository campistaRepository,
                               CampistaMapper campistaMapper) {
        this.campistaRepository = campistaRepository;
        this.campistaMapper = campistaMapper;
    }

    @Override
    public boolean campistaEhExistentePorCpf(String cpf) {
        return this.campistaRepository.existsByPessoa_Cpf(cpf) ;
    }

    @Override
    public boolean campistaEhExistentePorId(Long id) {
        return this.campistaRepository.existsById(id);
    }

    @Override
    public void inserirCampista(CampistaEntityDomain campistaDomain) {
        this.campistaRepository.save(this.campistaMapper.toEntity(campistaDomain));
    }

    @Override
    public List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNaEquipe(Long idEquipe) {
        return this.campistaRepository
                .findAllByEquipe_Id(idEquipe)
                .stream()
                .map(campistaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNoCodigoRegistro(String codigoRegistro) {
        return this.campistaRepository
                .findAllByCodigoRegistroOrderByPessoa_NomeCompleto(codigoRegistro)
                .stream()
                .map(campistaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public CampistaEntityDomain buscarCampistaPorId(Long id) {
        return this.campistaRepository
                .findById(id)
                .map(campistaMapper::toDomain)
                .orElse(null) ;
    }

    @Override
    public CampistaEntityDomain buscarCampistaPorCpf(String cpf) {
        return this.campistaMapper.toDomain(this.campistaRepository.findByPessoa_Cpf(cpf));
    }

    @Override
    public CampistaEntityDomain buscarCampistaNoAcampamentoPorCpf(Long idAcampamento, String cpf) {
        return this.campistaMapper.toDomain(this.campistaRepository.findByEquipe_Acampamento_IdAndPessoa_Cpf(idAcampamento, cpf));
    }
}
