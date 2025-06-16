package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.PessoaMedicamentoGateway;
import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.domain.entity.PessoaEntityDomain;
import com.camp.manager.domain.entity.PessoaMedicamentoEntityDomain;
import com.camp.manager.infra.mapper.MedicamentoMapper;
import com.camp.manager.infra.mapper.PessoaMapper;
import com.camp.manager.infra.mapper.PessoaMedicamentoMapper;
import com.camp.manager.infra.persistence.entity.MedicamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.PessoaEntityJpa;
import com.camp.manager.infra.persistence.entity.PessoaMedicamentoControllerEntityJpa;
import com.camp.manager.infra.persistence.repository.PessoaMedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaMedicamentoGatewayImpl implements PessoaMedicamentoGateway {

    private final PessoaMedicamentoRepository pessoaMedicamentoRepository;
    private final PessoaMedicamentoMapper pessoaMedicamentoMapper;
    private final PessoaMapper pessoaMapper;
    private final MedicamentoMapper medicamentoMapper;

    @Autowired
    public PessoaMedicamentoGatewayImpl(PessoaMedicamentoRepository pessoaMedicamentoRepository,
                                        PessoaMedicamentoMapper pessoaMedicamentoMapper,
                                        PessoaMapper pessoaMapper,
                                        MedicamentoMapper medicamentoMapper) {
        this.pessoaMedicamentoRepository = pessoaMedicamentoRepository;
        this.pessoaMedicamentoMapper = pessoaMedicamentoMapper;
        this.pessoaMapper = pessoaMapper;
        this.medicamentoMapper = medicamentoMapper;
    }


    @Override
    public void salvarPessoasMedicamento(PessoaEntityDomain pessoaEntity, MedicamentoEntityDomain medicamentoEntity) {
        PessoaEntityJpa pessoaEntityJpa = this.pessoaMapper.toEntity(pessoaEntity);
        MedicamentoEntityJpa medicamentoEntityJpa = this.medicamentoMapper.toEntity(medicamentoEntity);

        this.pessoaMedicamentoRepository.save(new PessoaMedicamentoControllerEntityJpa(null,pessoaEntityJpa, medicamentoEntityJpa));
    }

    @Override
    public List<PessoaMedicamentoEntityDomain> buscarMedicamentosPorPessoa(PessoaEntityDomain pessoaEntityDomain) {
        return this.pessoaMedicamentoRepository.findAllByPessoa(this.pessoaMapper.toEntity(pessoaEntityDomain))
                .stream()
                .map(pessoaMedicamentoMapper::toDomain)
                .toList();
    }
}
