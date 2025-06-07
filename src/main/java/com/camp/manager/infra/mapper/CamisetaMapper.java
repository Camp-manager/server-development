package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.CamisetaEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.enums.TamanhoCamiseta;
import com.camp.manager.infra.persistence.entity.CamisetaEntityJpa;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class CamisetaMapper implements Mapper<CamisetaEntityJpa, CamisetaEntityDomain>{

    private final TemaMapper mapperTema;

    @Autowired
    public CamisetaMapper(@Lazy TemaMapper mapperTema) {
        this.mapperTema = mapperTema;
    }

    @Override
    public CamisetaEntityDomain toDomain(CamisetaEntityJpa camisetaEntityJpa) {
        return new CamisetaEntityDomain(
                camisetaEntityJpa.getId(),
                camisetaEntityJpa.getTamanhoCamiseta().getDescricao(),
                this.mapperTema.toDomain(camisetaEntityJpa.getTema())
        );
    }

    @Override
    public CamisetaEntityJpa toEntity(CamisetaEntityDomain camisetaEntityDomain) {
        return new CamisetaEntityJpa(
                camisetaEntityDomain.id(),
                TamanhoCamiseta.fromDescricao(camisetaEntityDomain.tamanhoCamiseta()),
                this.mapperTema.toEntity(camisetaEntityDomain.tema())
        );
    }
}
