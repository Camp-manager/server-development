package com.camp.manager.infra.persistence.mapper;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import org.springframework.stereotype.Component;

@Component
public class AcampamentoMapper implements Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> {

    @Override
    public AcampamentoEntityDomain toDomain(AcampamentoEntityJpa acampamentoEntityJpa) {
        return null;
    }

    @Override
    public AcampamentoEntityJpa toEntity(AcampamentoEntityDomain acampamentoEntityDomain) {
        return null;
    }
}
