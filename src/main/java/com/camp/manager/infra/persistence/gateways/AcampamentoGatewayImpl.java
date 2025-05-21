package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.infra.persistence.repository.AcampamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AcampamentoGatewayImpl implements AcampamentoGateway {

    private final AcampamentoRepository acampamentoRepository;

    @Autowired
    public AcampamentoGatewayImpl(AcampamentoRepository acampamentoRepository) {
        this.acampamentoRepository = acampamentoRepository;
    }

    @Override
    public boolean existsAcampamentoById(Long idAcampamento) {
        return this.acampamentoRepository.existsById(idAcampamento);
    }
}
