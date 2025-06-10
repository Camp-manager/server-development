package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.*;
import com.camp.manager.infra.persistence.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class FuncionarioMapper implements Mapper<FuncionarioEntityJpa, FuncionarioEntityDomain>{

    private final CarteirinhaMapper mapperCarteirinha;
    private final CamisetaMapper mapperCamiseta;
    private final EquipeMapper mapperEquipe;

    @Autowired
    public FuncionarioMapper(@Lazy CarteirinhaMapper mapperCarteirinha,
                             @Lazy CamisetaMapper mapperCamiseta,
                             @Lazy EquipeMapper mapperEquipe) {
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
                funcionarioEntityJpa.getEmail(),
                funcionarioEntityJpa.getCodigoRegistro(),
                funcionarioEntityJpa.getHabilidade(),
                funcionarioEntityJpa.getCamiseta() != null ? this.mapperCamiseta.toDomain(funcionarioEntityJpa.getCamiseta()) : null,
                funcionarioEntityJpa.getEquipe() != null ? this.mapperEquipe.toDomain(funcionarioEntityJpa.getEquipe()) : null,
                funcionarioEntityJpa.getCarteirinha() != null ? this.mapperCarteirinha.toDomain(funcionarioEntityJpa.getCarteirinha()) : null
        );
    }

    @Override
    public FuncionarioEntityJpa toEntity(FuncionarioEntityDomain funcionarioEntityDomain) {
        return new FuncionarioEntityJpa(
                funcionarioEntityDomain.id(),
                funcionarioEntityDomain.nome(),
                funcionarioEntityDomain.cpf(),
                funcionarioEntityDomain.telefone(),
                funcionarioEntityDomain.email(),
                funcionarioEntityDomain.codigoRegistro(),
                funcionarioEntityDomain.habilidade(),
                funcionarioEntityDomain.camiseta() != null ? this.mapperCamiseta.toEntity(funcionarioEntityDomain.camiseta()) : null,
                funcionarioEntityDomain.equipe() != null ? this.mapperEquipe.toEntity(funcionarioEntityDomain.equipe()) : null,
                funcionarioEntityDomain.carteirinha() != null ? this.mapperCarteirinha.toEntity(funcionarioEntityDomain.carteirinha()) : null
        );
    }
}
