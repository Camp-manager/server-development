package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import com.camp.manager.infra.persistence.entity.TipoAcampamentoEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AcampamentoMapper implements Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> {

    private final Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema;
    private final Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> mapperTipo;

    @Autowired
    public AcampamentoMapper(Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema,
                             Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain> mapperTipo) {
        this.mapperTema = mapperTema;
        this.mapperTipo = mapperTipo;
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
                mapperTipo.toDomain(acampamentoEntityJpa.getTipoAcampamento())
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
                mapperTipo.toEntity(acampamentoEntityDomain.tipoAcampamento())
        );
    }
}
