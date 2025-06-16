package com.camp.manager.infra.mapper;

import com.camp.manager.domain.entity.PessoaMedicamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.PessoaMedicamentoControllerEntityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PessoaMedicamentoMapper implements Mapper<PessoaMedicamentoControllerEntityJpa, PessoaMedicamentoEntityDomain> {

    private final MedicamentoMapper medicamentoMapper;
    private final PessoaMapper pessoaMapper;

    @Autowired
    public PessoaMedicamentoMapper(MedicamentoMapper medicamentoMapper, PessoaMapper pessoaMapper) {
        this.medicamentoMapper = medicamentoMapper;
        this.pessoaMapper = pessoaMapper;
    }


    @Override
    public PessoaMedicamentoEntityDomain toDomain(PessoaMedicamentoControllerEntityJpa pessoaMedicamentoControllerEntityJpa) {
        return new PessoaMedicamentoEntityDomain(
                pessoaMedicamentoControllerEntityJpa.getId(),
                this.pessoaMapper.toDomain(pessoaMedicamentoControllerEntityJpa.getPessoa()),
                this.medicamentoMapper.toDomain(pessoaMedicamentoControllerEntityJpa.getMedicamento())
        );
    }

    @Override
    public PessoaMedicamentoControllerEntityJpa toEntity(PessoaMedicamentoEntityDomain pessoaMedicamentoEntityDomain) {
        return new PessoaMedicamentoControllerEntityJpa(
                pessoaMedicamentoEntityDomain.id(),
                this.pessoaMapper.toEntity(pessoaMedicamentoEntityDomain.pessoa()),
                this.medicamentoMapper.toEntity(pessoaMedicamentoEntityDomain.medicamento())
        );
    }
}
