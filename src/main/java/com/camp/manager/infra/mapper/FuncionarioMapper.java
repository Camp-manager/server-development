package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper implements Mapper<FuncionarioEntityJpa, FuncionarioEntityDomain>{

    private final Mapper<CarteirinhaEntityJpa, CarteirinhaEntityDomain> mapperCarteirinha;
    private final Mapper<CamisetaEntityJpa, CamisetaEntityDomain> mapperCamiseta;
    private final Mapper<EquipeEntityJpa, EquipeEntityDomain> mapperEquipe;

    @Autowired
    public FuncionarioMapper(Mapper<CarteirinhaEntityJpa, CarteirinhaEntityDomain> mapperCarteirinha,
                             Mapper<CamisetaEntityJpa, CamisetaEntityDomain> mapperCamiseta,
                             Mapper<EquipeEntityJpa, EquipeEntityDomain> mapperEquipe) {
        this.mapperCarteirinha = mapperCarteirinha;
        this.mapperCamiseta = mapperCamiseta;
        this.mapperEquipe = mapperEquipe;
    }

    @Override
    public FuncionarioEntityDomain toDomain(FuncionarioEntityJpa funcionarioEntityJpa) {
        return new FuncionarioEntityDomain(
                funcionarioEntityJpa.getId(),
                funcionarioEntityJpa.getNome(),
                funcionarioEntityJpa.getCpf(),
                funcionarioEntityJpa.getTelefone(),
                null,
                funcionarioEntityJpa.getCodigoRegistro(),
                funcionarioEntityJpa.getHabilidade(),
                this.mapperCamiseta.toDomain(funcionarioEntityJpa.getCamiseta()),
                this.mapperEquipe.toDomain(funcionarioEntityJpa.getEquipe()),
                this.mapperCarteirinha.toDomain(funcionarioEntityJpa.getCarteirinha())
        );
    }

    @Override
    public FuncionarioEntityJpa toEntity(FuncionarioEntityDomain funcionarioEntityDomain) {
        return new FuncionarioEntityJpa(
                funcionarioEntityDomain.id(),
                funcionarioEntityDomain.nome(),
                funcionarioEntityDomain.cpf(),
                funcionarioEntityDomain.telefone(),
                funcionarioEntityDomain.codigoRegistro(),
                funcionarioEntityDomain.habilidade(),
                this.mapperCamiseta.toEntity(funcionarioEntityDomain.camiseta()),
                this.mapperEquipe.toEntity(funcionarioEntityDomain.equipe()),
                this.mapperCarteirinha.toEntity(funcionarioEntityDomain.carteirinha())
        );
    }
}
