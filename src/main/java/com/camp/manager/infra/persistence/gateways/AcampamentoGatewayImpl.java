package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.AcampamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcampamentoGatewayImpl implements AcampamentoGateway {

    private final AcampamentoRepository acampamentoRepository;
    private final Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapper;

    @Autowired
    public AcampamentoGatewayImpl(AcampamentoRepository acampamentoRepository, Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapper) {
        this.acampamentoRepository = acampamentoRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean existsAcampamentoById(Long idAcampamento) {
        return this.acampamentoRepository.existsById(idAcampamento);
    }

    @Override
    public AcampamentoEntityDomain buscarAcampamentoPorId(Long idAcampamento) {
        return mapper.toDomain(this.acampamentoRepository.findById(idAcampamento)
                .orElseThrow(() -> new NotFoundException("Acampamento com id ["+idAcampamento+"] não encontrado!")));
    }
}
