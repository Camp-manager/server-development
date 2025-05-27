package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;

import java.util.List;

public interface TipoAcampamentoGateway {
    List<TipoAcampamentoEntityDomain> buscarTodosTiposDeAcampamento();
}
