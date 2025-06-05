package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.CampistaEntityDomain;

import java.util.List;

public interface CampistaGateway {
    boolean campistaEhExistentePorCpf(String cpf);
    void inserirCampista(CampistaEntityDomain campistaDomain);
    List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNaEquipe(Long id);
    List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNoCodigoRegistro(String codigoRegistro);
}
