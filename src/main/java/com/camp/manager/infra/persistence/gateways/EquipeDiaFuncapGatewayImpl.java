package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.EquipeDiaFuncaoGateway;
import com.camp.manager.domain.entity.EquipeDiaFuncaoEntityDomain;
import com.camp.manager.infra.mapper.EquipeDiaFuncaoMapper;
import com.camp.manager.infra.persistence.entity.EquipeDiaFuncaoEntityJpa;
import com.camp.manager.infra.persistence.repository.EquipeDiaFuncaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EquipeDiaFuncapGatewayImpl implements EquipeDiaFuncaoGateway {

    private final EquipeDiaFuncaoRepository equipeDiaFuncaoRepository;
    private final EquipeDiaFuncaoMapper equipeDiaFuncaoMapper;

    @Autowired
    public EquipeDiaFuncapGatewayImpl(EquipeDiaFuncaoRepository equipeDiaFuncaoRepository, EquipeDiaFuncaoMapper equipeDiaFuncaoMapper) {
        this.equipeDiaFuncaoRepository = equipeDiaFuncaoRepository;
        this.equipeDiaFuncaoMapper = equipeDiaFuncaoMapper;
    }

    @Override
    public void salvarTodosEquipeDiaFuncao(List<EquipeDiaFuncaoEntityDomain> diasDeFuncoesDasEquipes) {
        List<EquipeDiaFuncaoEntityJpa> diasDeFuncoesDasEquipesJpa = diasDeFuncoesDasEquipes.stream()
                .map(equipeDiaFuncaoMapper::toEntity)
                .collect(Collectors.toList());
        this.equipeDiaFuncaoRepository.saveAll(diasDeFuncoesDasEquipesJpa);
    }
}
