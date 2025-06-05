package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.CarteirinhaEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.infra.persistence.entity.CarteirinhaEntityJpa;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarteirinhaMapper implements Mapper<CarteirinhaEntityJpa, CarteirinhaEntityDomain>{

    private final Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema;

    @Autowired
    public CarteirinhaMapper(Mapper<TemaEntityJpa, TemaEntityDomain> mapperTema) {
        this.mapperTema = mapperTema;
    }

    @Override
    public CarteirinhaEntityDomain toDomain(CarteirinhaEntityJpa carteirinhaEntityJpa) {
        return new CarteirinhaEntityDomain(
                carteirinhaEntityJpa.getId(),
                carteirinhaEntityJpa.getTextoApresentacao(),
                this.mapperTema.toDomain(carteirinhaEntityJpa.getTema())
        );
    }

    @Override
    public CarteirinhaEntityJpa toEntity(CarteirinhaEntityDomain carteirinhaEntityDomain) {
        return new CarteirinhaEntityJpa(
                carteirinhaEntityDomain.id(),
                carteirinhaEntityDomain.textoApresentacao(),
                this.mapperTema.toEntity(carteirinhaEntityDomain.tema())
        );
    }
}
