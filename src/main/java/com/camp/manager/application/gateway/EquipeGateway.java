package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.EquipeEntityDomain;

import java.util.List;

public interface EquipeGateway {
    void inserirTodosAsEquipesDeTrabalho(List<EquipeEntityDomain> equipesDeTrabalho);
    List<EquipeEntityDomain> buscarTodasEquipesDeTrabalho(Long idAcampamento);
    boolean equipeEhExistentePorId(Long idEquipe);
    void salvarEquipe(EquipeEntityDomain equipeEntityDomain);
    EquipeEntityDomain buscarEquipePorId(Long idEquipe);
}
