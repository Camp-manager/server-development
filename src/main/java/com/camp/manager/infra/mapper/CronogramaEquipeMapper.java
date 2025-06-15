package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.infra.persistence.entity.CronogramaEquipeEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CronogramaEquipeMapper implements Mapper<CronogramaEquipeEntityJpa, CronogramaEquipeEntityDomain> {

    private final EquipeMapper equipeMapper;
    private final AtividadeMapper atividadeMapper;

    @Autowired
    public CronogramaEquipeMapper(@Lazy EquipeMapper equipeMapper, @Lazy AtividadeMapper atividadeMapper) {
        this.equipeMapper = equipeMapper;
        this.atividadeMapper = atividadeMapper;
    }

    @Override
    public CronogramaEquipeEntityDomain toDomain(CronogramaEquipeEntityJpa cronogramaEntityJpa) {
        return new CronogramaEquipeEntityDomain(
                cronogramaEntityJpa.getId(),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataInicio()),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataFinal()),
                cronogramaEntityJpa.getDescricao(),
                this.equipeMapper.toDomain(cronogramaEntityJpa.getEquipe()),
                this.atividadeMapper.toDomainList(cronogramaEntityJpa.getAtividades())
        );
    }

    @Override
    public CronogramaEquipeEntityJpa toEntity(CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain) {
        return new CronogramaEquipeEntityJpa(
                cronogramaEquipeEntityDomain.id(),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataInicio()),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataFinal()),
                cronogramaEquipeEntityDomain.descricao(),
                this.equipeMapper.toEntity(cronogramaEquipeEntityDomain.equipe())
        );
    }
}
