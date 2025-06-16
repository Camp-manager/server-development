package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.EquipeDiaFuncaoEntityDomain;
import com.camp.manager.infra.persistence.entity.EquipeDiaFuncaoEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EquipeDiaFuncaoMapper implements Mapper<EquipeDiaFuncaoEntityJpa, EquipeDiaFuncaoEntityDomain>{

    private final EquipeMapper equipeMapper;

    @Autowired
    public EquipeDiaFuncaoMapper(@Lazy EquipeMapper equipeMapper) {
        this.equipeMapper = equipeMapper;
    }

    @Override
    public EquipeDiaFuncaoEntityDomain toDomain(EquipeDiaFuncaoEntityJpa equipeDiaFuncaoEntityJpa) {
        return new EquipeDiaFuncaoEntityDomain(
                equipeDiaFuncaoEntityJpa.getId(),
                equipeDiaFuncaoEntityJpa.getFuncao(),
                equipeDiaFuncaoEntityJpa.getData(),
                this.equipeMapper.toDomainWithoutRelationships(equipeDiaFuncaoEntityJpa.getEquipe())
        );
    }

    @Override
    public EquipeDiaFuncaoEntityJpa toEntity(EquipeDiaFuncaoEntityDomain equipeDiaFuncaoEntityDomain) {
        return new EquipeDiaFuncaoEntityJpa(
                equipeDiaFuncaoEntityDomain.id(),
                equipeDiaFuncaoEntityDomain.funcao(),
                equipeDiaFuncaoEntityDomain.data(),
                this.equipeMapper.toEntity(equipeDiaFuncaoEntityDomain.equipe())
        );
    }
}
