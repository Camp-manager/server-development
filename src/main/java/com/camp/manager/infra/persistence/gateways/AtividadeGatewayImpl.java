package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.AtividadeGateway;
import com.camp.manager.domain.entity.AtividadeEntityDomain;
import com.camp.manager.infra.mapper.AtividadeMapper;
import com.camp.manager.infra.persistence.entity.AtividadeEntityJpa;
import com.camp.manager.infra.persistence.repository.AtividadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AtividadeGatewayImpl implements AtividadeGateway {

    private final AtividadeRepository atividadeRepository;
    private final AtividadeMapper atividadeMapper;

    @Autowired
    public AtividadeGatewayImpl(AtividadeRepository atividadeRepository,
                                AtividadeMapper atividadeMapper) {
        this.atividadeRepository = atividadeRepository;
        this.atividadeMapper = atividadeMapper;
    }

    @Override
    public void salvarTodasAtividades(List<AtividadeEntityDomain> atividadesParaSalvar) {
        List<AtividadeEntityJpa> atividadesJpa = atividadesParaSalvar.stream()
                .map(atividadeMapper::toEntity)
                .toList();
        this.atividadeRepository.saveAll(atividadesJpa);
    }
}
