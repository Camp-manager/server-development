package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.EquipeDiaFuncaoEntityDomain;

import java.util.List;

public interface EquipeDiaFuncaoGateway {
    void salvarTodosEquipeDiaFuncao(List<EquipeDiaFuncaoEntityDomain> diasDeFuncoesDasEquipes);
}
