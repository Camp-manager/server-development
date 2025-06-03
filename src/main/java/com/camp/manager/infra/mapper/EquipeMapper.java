package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.CronogramaEntityJpa;
import com.camp.manager.infra.persistence.entity.EquipeEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapper implements Mapper<EquipeEntityJpa, EquipeEntityDomain>{

    private final Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma;
    private final Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapperAcampamento;

    @Autowired
    public EquipeMapper(@Lazy Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma,
                        @Lazy Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapperAcampamento) {
        this.mapperCronograma = mapperCronograma;
        this.mapperAcampamento = mapperAcampamento;
    }

    @Override
    public EquipeEntityDomain toDomain(EquipeEntityJpa equipeEntityJpa) {
        return new EquipeEntityDomain(
                equipeEntityJpa.getId(),
                equipeEntityJpa.getNome(),
                equipeEntityJpa.getTipoEquipe().getDescricao(),
                mapperCronograma.toDomain(equipeEntityJpa.getCronograma()),
                mapperAcampamento.toDomain(equipeEntityJpa.getAcampamento())
        );
    }

    @Override
    public EquipeEntityJpa toEntity(EquipeEntityDomain equipeEntityDomain) {
        return new EquipeEntityJpa(
                equipeEntityDomain.id(),
                equipeEntityDomain.nome(),
                TipoEquipe.fromDescricao(equipeEntityDomain.tipoEquipe()),
                mapperCronograma.toEntity(equipeEntityDomain.cronograma()),
                mapperAcampamento.toEntity(equipeEntityDomain.acampamento())
        );
    }

}
