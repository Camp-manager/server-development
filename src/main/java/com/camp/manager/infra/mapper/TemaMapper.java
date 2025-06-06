package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Base64;

@Component
public class TemaMapper implements Mapper<TemaEntityJpa, TemaEntityDomain> {

    @Override
    public TemaEntityDomain toDomain(TemaEntityJpa temaEntityJpa) {
        return new TemaEntityDomain(
                temaEntityJpa.getId(),
                temaEntityJpa.getDescricao(),
                temaEntityJpa.getDesign() != null ? Base64.getDecoder().decode(temaEntityJpa.getDesign()) : null,
                BigDecimal.valueOf(temaEntityJpa.getPrecoCamiseta()),
                BigDecimal.valueOf(temaEntityJpa.getPrecoAcampamento())
                );
    }

    @Override
    public TemaEntityJpa toEntity(TemaEntityDomain temaEntityDomain) {
        return new TemaEntityJpa(
                temaEntityDomain.id(),
                temaEntityDomain.descricao(),
                temaEntityDomain.imagemDesign() != null ? Base64.getEncoder().encodeToString(temaEntityDomain.imagemDesign()) : null,
                temaEntityDomain.precoCamiseta().doubleValue(),
                temaEntityDomain.precoAcampamento().doubleValue()
        );
    }
}
