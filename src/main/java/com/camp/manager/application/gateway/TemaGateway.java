package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.TemaEntityDomain;

public interface TemaGateway {
    boolean temaEhExistentePorDescricao(String descricaoDoTema);
    void inserirTema(TemaEntityDomain temaDomain);
    boolean temaEhExistentePorId(Long idTema);
    TemaEntityDomain buscarTemaPorId(Long idTema);
    void atualizarTema(TemaEntityDomain temaDomain);
}
