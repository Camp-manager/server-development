package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;

public interface TemaGateway {
    boolean temaEhExistente(String descricaoDoTema);
    void inserirTema(TemaEntityDomain temaDomain);
}
