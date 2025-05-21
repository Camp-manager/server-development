package com.camp.manager.infra.persistence.mapper;

import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.domain.enums.CategoriaTipoAcampamento;
import com.camp.manager.infra.persistence.entity.TipoAcampamentoEntityJpa;
import org.springframework.stereotype.Component;

@Component
public class TipoAcampamentoMapper implements Mapper<TipoAcampamentoEntityJpa, TipoAcampamentoEntityDomain>{
    @Override
    public TipoAcampamentoEntityDomain toDomain(TipoAcampamentoEntityJpa tipoAcampamentoEntityJpa) {
        return new TipoAcampamentoEntityDomain(
                tipoAcampamentoEntityJpa.getId(),
                tipoAcampamentoEntityJpa.getDescricao(),
                tipoAcampamentoEntityJpa.getCategoriaTipoAcampamento().getDescricao()
        );
    }

    @Override
    public TipoAcampamentoEntityJpa toEntity(TipoAcampamentoEntityDomain tipoAcampamentoEntityDomain) {
        return new TipoAcampamentoEntityJpa(
                tipoAcampamentoEntityDomain.id(),
                tipoAcampamentoEntityDomain.descricao(),
                CategoriaTipoAcampamento.fromDescricao(tipoAcampamentoEntityDomain.categoriaDoAcampamento())
        );
    }
}
