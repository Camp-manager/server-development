package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.infra.persistence.entity.CronogramaEquipeEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.stereotype.Component;

@Component
public class CronogramaEquipeMapper implements Mapper<CronogramaEquipeEntityJpa, CronogramaEquipeEntityDomain> {

    @Override
    public CronogramaEquipeEntityDomain toDomain(CronogramaEquipeEntityJpa cronogramaEntityJpa) {
        return new CronogramaEquipeEntityDomain(
                cronogramaEntityJpa.getId(),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataInicio()),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataFinal()),
                cronogramaEntityJpa.getDescricao(),
                cronogramaEntityJpa.getEquipeId()
        );
    }

    @Override
    public CronogramaEquipeEntityJpa toEntity(CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain) {
        return new CronogramaEquipeEntityJpa(
                cronogramaEquipeEntityDomain.id(),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataInicio()),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataFinal()),
                cronogramaEquipeEntityDomain.descricao(),
                cronogramaEquipeEntityDomain.idEquipe()
        );
    }
}
