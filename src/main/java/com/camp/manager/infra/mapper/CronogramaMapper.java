package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.infra.persistence.entity.CronogramaEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.stereotype.Component;

@Component
public class CronogramaMapper implements Mapper<CronogramaEntityJpa, CronogramaEntityDomain> {

    @Override
    public CronogramaEntityDomain toDomain(CronogramaEntityJpa cronogramaEntityJpa) {
        return new CronogramaEntityDomain(
                cronogramaEntityJpa.getId(),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataInicio()),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataFinal()),
                cronogramaEntityJpa.getDescricao()
        );
    }

    @Override
    public CronogramaEntityJpa toEntity(CronogramaEntityDomain cronogramaEntityDomain) {
        return new CronogramaEntityJpa(
                cronogramaEntityDomain.id(),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEntityDomain.dataInicio()),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEntityDomain.dataFinal()),
                cronogramaEntityDomain.descricao()
        );
    }
}
