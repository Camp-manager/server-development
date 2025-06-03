package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.EnderecoEntityDomain;
import com.camp.manager.infra.persistence.entity.EnderecoEntityJpa;
import org.springframework.stereotype.Component;

@Component
public class EnderecoMapper implements Mapper<EnderecoEntityJpa, EnderecoEntityDomain> {

    @Override
    public EnderecoEntityDomain toDomain(EnderecoEntityJpa enderecoEntityJpa) {
        return new EnderecoEntityDomain(
                enderecoEntityJpa.getId(),
                enderecoEntityJpa.getCep(),
                enderecoEntityJpa.getRua(),
                Long.parseLong(enderecoEntityJpa.getNumero()),
                enderecoEntityJpa.getCidade(),
                enderecoEntityJpa.getBairro(),
                enderecoEntityJpa.getPontoReferencia()
        );
    }

    @Override
    public EnderecoEntityJpa toEntity(EnderecoEntityDomain enderecoEntityDomain) {
        return new EnderecoEntityJpa(
                enderecoEntityDomain.id(),
                enderecoEntityDomain.cep(),
                enderecoEntityDomain.rua(),
                String.valueOf(enderecoEntityDomain.numero()),
                enderecoEntityDomain.cidade(),
                enderecoEntityDomain.bairro(),
                enderecoEntityDomain.pontoReferencia()
        );
    }
}
