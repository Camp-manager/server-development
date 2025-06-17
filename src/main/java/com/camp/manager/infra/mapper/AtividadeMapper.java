package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.AtividadeEntityDomain;
import com.camp.manager.domain.enums.TipoAtividade;
import com.camp.manager.infra.persistence.entity.AtividadeEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.List;

@Component
public class AtividadeMapper implements Mapper<AtividadeEntityJpa, AtividadeEntityDomain> {

    private final CronogramaEquipeMapper cronogramaMapper;

    @Autowired
    public AtividadeMapper(@Lazy CronogramaEquipeMapper cronogramaMapper) {
        this.cronogramaMapper = cronogramaMapper;
    }

    @Override
    public AtividadeEntityDomain toDomain(AtividadeEntityJpa atividadeEntityJpa) {
        return new AtividadeEntityDomain(
                atividadeEntityJpa.getId(),
                atividadeEntityJpa.getTipoAtividade().getDescricao(),
                LocalTime.parse(atividadeEntityJpa.getHorario()),
                atividadeEntityJpa.getDescricao(),
                this.cronogramaMapper.toDomainWithoutAtividades(atividadeEntityJpa.getCronograma())
        );
    }

    @Override
    public AtividadeEntityJpa toEntity(AtividadeEntityDomain atividadeEntityDomain) {
        return new AtividadeEntityJpa(
                atividadeEntityDomain.id(),
                TipoAtividade.fromDescricao(atividadeEntityDomain.tipoAtividade()),
                atividadeEntityDomain.horario().toString(),
                atividadeEntityDomain.descricao(),
                cronogramaMapper.toEntity(atividadeEntityDomain.cronograma())
        );
    }

    public List<AtividadeEntityDomain> toDomainList(List<AtividadeEntityJpa> atividadeEntityJpaList) {
        return atividadeEntityJpaList.stream()
                .map(this::toDomain)
                .toList();
    }

    public List<AtividadeEntityJpa> toEntityList(List<AtividadeEntityDomain> atividadeEntityDomainList) {
        return atividadeEntityDomainList.stream()
                .map(this::toEntity)
                .toList();
    }
}
