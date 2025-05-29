package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;

public interface AcampamentoGateway {
    boolean existsAcampamentoById(Long idAcampamento);
    AcampamentoEntityDomain buscarAcampamentoPorId(Long idAcampamento);
    AcampamentoEntityDomain inserirAcampamento(AcampamentoEntityDomain acampamentoGerado);
}
