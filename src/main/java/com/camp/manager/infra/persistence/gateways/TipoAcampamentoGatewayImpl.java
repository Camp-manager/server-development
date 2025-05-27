package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.TipoAcampamentoGateway;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.TipoAcampamentoEntityJpa;
import com.camp.manager.infra.persistence.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.TipoAcampamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipoAcampamentoGatewayImpl implements TipoAcampamentoGateway {

    private final TipoAcampamentoRepository tipoAcampamentoRepository;
    private final Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> tipoAcampamentoMapper;

    @Autowired
    public TipoAcampamentoGatewayImpl(TipoAcampamentoRepository tipoAcampamentoRepository, Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> tipoAcampamentoMapper) {
        this.tipoAcampamentoRepository = tipoAcampamentoRepository;
        this.tipoAcampamentoMapper = tipoAcampamentoMapper;
    }

    @Override
    public List<TipoAcampamentoEntityDomain> buscarTodosTiposDeAcampamento() {
        return this.tipoAcampamentoRepository.findAll()
                .stream()
                .map(tipoAcampamentoMapper::toDomain)
                .toList();
    }
}
