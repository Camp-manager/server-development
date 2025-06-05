package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.entity.CampistaEntityJpa;
import com.camp.manager.infra.persistence.repository.CampistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CampistaGatewayImpl implements CampistaGateway {

    private final CampistaRepository campistaRepository;
    private final Mapper<CampistaEntityJpa, CampistaEntityDomain> campistaMapper;

    @Autowired
    public CampistaGatewayImpl(CampistaRepository campistaRepository,
                               Mapper<CampistaEntityJpa, CampistaEntityDomain> campistaMapper) {
        this.campistaRepository = campistaRepository;
        this.campistaMapper = campistaMapper;
    }

    @Override
    public boolean campistaEhExistentePorCpf(String cpf) {
        return this.campistaRepository.existsByPessoa_Cpf(cpf) ;
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
                .toList();
    }

    @Override
    public List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNoCodigoRegistro(String codigoRegistro) {
        return this.campistaRepository
                .findAllByCodigoRegistroOrderByPessoa_NomeCompleto(codigoRegistro)
                .stream()
                .map(campistaMapper::toDomain)
                .toList();
    }
}
