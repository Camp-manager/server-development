package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import com.camp.manager.infra.persistence.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TemaGatewayImpl implements TemaGateway {

    private final TemaRepository temaRepository;
    private final Mapper<TemaEntityJpa, TemaEntityDomain> temaMapper;

    @Autowired
    public TemaGatewayImpl(TemaRepository temaRepository, Mapper<TemaEntityJpa, TemaEntityDomain> temaMapper) {
        this.temaRepository = temaRepository;
        this.temaMapper = temaMapper;
    }

    @Override
    public boolean temaEhExistente(String descricaoDoTema) {
        return this.temaRepository.existsByDescricao(descricaoDoTema);
    }

    @Override
    public void inserirTema(TemaEntityDomain temaDomain) {
        this.temaRepository.save(temaMapper.toEntity(temaDomain));
    }
}
