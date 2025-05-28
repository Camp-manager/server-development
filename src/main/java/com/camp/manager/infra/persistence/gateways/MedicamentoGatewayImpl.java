package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.MedicamentoGateway;
import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.MedicamentoEntityJpa;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.MedicamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicamentoGatewayImpl implements MedicamentoGateway {

    private final MedicamentoRepository medicamentoRepository;
    private final Mapper<MedicamentoEntityJpa, MedicamentoEntityDomain> medicamentoMapper;

    @Autowired
    public MedicamentoGatewayImpl(MedicamentoRepository medicamentoRepository,
                                  Mapper<MedicamentoEntityJpa, MedicamentoEntityDomain> medicamentoMapper) {
        this.medicamentoRepository = medicamentoRepository;
        this.medicamentoMapper = medicamentoMapper;
    }

    @Override
    public List<MedicamentoEntityDomain> buscarTodosMedicamentos() {
        return this.medicamentoRepository
                .findAllByOrderByNomeAsc()
                .stream()
                .map(medicamentoMapper::toDomain)
                .collect(Collectors.toList());
    }
}
