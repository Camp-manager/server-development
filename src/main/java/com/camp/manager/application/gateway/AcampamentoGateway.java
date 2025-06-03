package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;

import java.util.List;

public interface AcampamentoGateway {
    boolean acampamentoEhExistentePorId(Long idAcampamento);
    AcampamentoEntityDomain buscarAcampamentoPorId(Long idAcampamento);
    void inserirAcampamento(AcampamentoEntityDomain acampamentoGerado);
    List<AcampamentoEntityDomain> buscarTodosOsAcampamentos();
    boolean acampamentoEhExistentePorCodigoRegistro(String codigoRegistro);
    AcampamentoEntityDomain buscarAcampamentoPorCodigoRegistro(String codigoRegistro);
}
