package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.AtividadeEntityDomain;

import java.util.List;

public interface AtividadeGateway {
    void salvarTodasAtividades(List<AtividadeEntityDomain> atividadesParaSalvar);
}
