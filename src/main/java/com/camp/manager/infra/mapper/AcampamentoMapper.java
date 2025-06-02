package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.CronogramaEntityJpa;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import com.camp.manager.infra.persistence.entity.TipoAcampamentoEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcampamentoMapper implements Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> {

    private final Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema;
    private final Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> mapperTipo;
    private final Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma;

    @Autowired
    public AcampamentoMapper(Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema,
                             Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> mapperTipo, Mapper<CronogramaEntityJpa, CronogramaEntityDomain> mapperCronograma) {
        this.mapperTema = mapperTema;
        this.mapperTipo = mapperTipo;
        this.mapperCronograma = mapperCronograma;
    }

    @Override
    public AcampamentoEntityDomain toDomain(AcampamentoEntityJpa acampamentoEntityJpa) {
        return new AcampamentoEntityDomain(
                acampamentoEntityJpa.getId(),
                acampamentoEntityJpa.getNome(),
                acampamentoEntityJpa.getLimiteCampistas(),
                acampamentoEntityJpa.getLimiteFuncionario(),
                acampamentoEntityJpa.getCodigoRegistro(),
                mapperTema.toDomain(acampamentoEntityJpa.getTema()),
                mapperTipo.toDomain(acampamentoEntityJpa.getTipoAcampamento()),
                mapperCronograma.toDomain(acampamentoEntityJpa.getCronograma())
        );
    }

    @Override
    public AcampamentoEntityJpa toEntity(AcampamentoEntityDomain acampamentoEntityDomain) {
        return new AcampamentoEntityJpa(
                acampamentoEntityDomain.id(),
                acampamentoEntityDomain.nome(),
                acampamentoEntityDomain.limiteCampistas(),
                acampamentoEntityDomain.limiteFuncionario(),
                acampamentoEntityDomain.codigoRegistro(),
                mapperTema.toEntity(acampamentoEntityDomain.tema()),
                mapperTipo.toEntity(acampamentoEntityDomain.tipoAcampamento()),
                mapperCronograma.toEntity(acampamentoEntityDomain.cronograma())
        );
    }
}
