package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.EnderecoEntityDomain;
import com.camp.manager.domain.entity.FamiliarEntityDomain;
import com.camp.manager.domain.enums.Parentesco;
import com.camp.manager.infra.persistence.entity.EnderecoEntityJpa;
import com.camp.manager.infra.persistence.entity.FamiliarEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FamiliarMapper implements Mapper<FamiliarEntityJpa, FamiliarEntityDomain> {

    private final Mapper<EnderecoEntityJpa, EnderecoEntityDomain> mapperEndereco;

    @Autowired
    public FamiliarMapper(@Lazy Mapper<EnderecoEntityJpa, EnderecoEntityDomain> mapperEndereco) {
        this.mapperEndereco = mapperEndereco;
    }

    @Override
    public FamiliarEntityDomain toDomain(FamiliarEntityJpa familiarEntityJpa) {
        return new FamiliarEntityDomain(
                familiarEntityJpa.getId(),
                familiarEntityJpa.getNome(),
                familiarEntityJpa.getParentesco().getDescricao(),
                familiarEntityJpa.getTelefone(),
                this.mapperEndereco.toDomain(familiarEntityJpa.getEndereco())
        );
    }

    @Override
    public FamiliarEntityJpa toEntity(FamiliarEntityDomain familiarEntityDomain) {
        return new FamiliarEntityJpa(
                familiarEntityDomain.id(),
                familiarEntityDomain.nome(),
                Parentesco.fromDescricao(familiarEntityDomain.parentesco()),
                familiarEntityDomain.telefone(),
                this.mapperEndereco.toEntity(familiarEntityDomain.endereco())
        );
    }
}
