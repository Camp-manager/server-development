package com.camp.manager.infra.persistence.mapper;

import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.domain.enums.TipoMedicamento;
import com.camp.manager.infra.persistence.entity.MedicamentoEntityJpa;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MedicamentoMapper implements Mapper<MedicamentoEntityJpa, MedicamentoEntityDomain>{

    @Override
    public MedicamentoEntityDomain toDomain(MedicamentoEntityJpa medicamentoEntityJpa) {
        return new MedicamentoEntityDomain(
                medicamentoEntityJpa.getId(),
                medicamentoEntityJpa.getNome(),
                medicamentoEntityJpa.getQuantidade(),
                medicamentoEntityJpa.getTipo().getDescricao(),
                BigDecimal.valueOf(medicamentoEntityJpa.getValor())
        );
    }

    @Override
    public MedicamentoEntityJpa toEntity(MedicamentoEntityDomain medicamentoEntityDomain) {
        return new MedicamentoEntityJpa(
                medicamentoEntityDomain.id(),
                medicamentoEntityDomain.nome(),
                medicamentoEntityDomain.quantidade(),
                TipoMedicamento.fromDescricao(medicamentoEntityDomain.tipo()),
                medicamentoEntityDomain.valor().doubleValue()
        );
    }
}
