package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.infra.persistence.entity.AtividadeEntityJpa;
import com.camp.manager.infra.persistence.entity.CronogramaEquipeEntityJpa;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

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
                this.equipeMapper.toDomainWithoutCronogramas(cronogramaEntityJpa.getEquipe()),
                this.atividadeMapper.toDomainList(cronogramaEntityJpa.getAtividades())
        );
    }

    public CronogramaEquipeEntityDomain toDomainWithoutAtividades(CronogramaEquipeEntityJpa cronogramaEntityJpa) {
        if (cronogramaEntityJpa == null) {
            return null;
        }
        return new CronogramaEquipeEntityDomain(
                cronogramaEntityJpa.getId(),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataInicio()),
                LocalDateConverterAPP.converterStringParaLocalDate(cronogramaEntityJpa.getDataFinal()),
                cronogramaEntityJpa.getDescricao(),
                this.equipeMapper.toDomainWithoutCronogramas(cronogramaEntityJpa.getEquipe()),
                new ArrayList<>()
        );
    }

    @Override
    public CronogramaEquipeEntityJpa toEntity(CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain) {
        CronogramaEquipeEntityJpa cronogramaEquipeJpa = new CronogramaEquipeEntityJpa(
                cronogramaEquipeEntityDomain.id(),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataInicio()),
                LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataFinal()),
                cronogramaEquipeEntityDomain.descricao(),
                this.equipeMapper.toEntity(cronogramaEquipeEntityDomain.equipe())
        );

        if (cronogramaEquipeEntityDomain.atividades() != null) {
            List<AtividadeEntityJpa> atividadesJpa = this.atividadeMapper.toEntityList(cronogramaEquipeEntityDomain.atividades());

            atividadesJpa.forEach(atividade -> atividade.setCronograma(cronogramaEquipeJpa));

            cronogramaEquipeJpa.setAtividades(atividadesJpa);
        }

        return cronogramaEquipeJpa;
    }
}
