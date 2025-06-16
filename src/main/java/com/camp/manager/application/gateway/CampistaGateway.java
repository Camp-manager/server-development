package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.CampistaEntityDomain;

import java.util.List;

public interface CampistaGateway {
    boolean campistaEhExistentePorCpf(String cpf);
    boolean campistaEhExistentePorId(Long id);
    void inserirCampista(CampistaEntityDomain campistaDomain);
    List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNaEquipe(Long id);
    List<CampistaEntityDomain> buscarTodosOsCampistasComBaseNoCodigoRegistro(String codigoRegistro);
    CampistaEntityDomain buscarCampistaPorId(Long id);
    CampistaEntityDomain buscarCampistaPorCpf(String cpf);
    CampistaEntityDomain buscarCampistaNoAcampamentoPorCpf(Long idAcampamento, String cpf);
    List<CampistaEntityDomain> buscarCampistasCompradoresCamisa(String s);
}
