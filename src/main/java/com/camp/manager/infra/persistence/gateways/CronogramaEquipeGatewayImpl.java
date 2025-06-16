package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.CronogramaEquipeGateway;
import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.infra.mapper.CronogramaEquipeMapper;
import com.camp.manager.infra.persistence.entity.CronogramaEquipeEntityJpa;
import com.camp.manager.infra.persistence.repository.CronogramaEquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CronogramaEquipeGatewayImpl implements CronogramaEquipeGateway {

    private final CronogramaEquipeRepository cronogramaEquipeRepository;
    private final CronogramaEquipeMapper cronogramaEquipeMapper;

    @Autowired
    public CronogramaEquipeGatewayImpl(CronogramaEquipeRepository cronogramaEquipeRepository,
                                       CronogramaEquipeMapper cronogramaEquipeMapper) {
        this.cronogramaEquipeRepository = cronogramaEquipeRepository;
        this.cronogramaEquipeMapper = cronogramaEquipeMapper;
    }

    @Override
    public CronogramaEquipeEntityDomain salvarCronogramaEquipe(CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain) {
        CronogramaEquipeEntityJpa cronogramaJpa = cronogramaEquipeMapper.toEntity(cronogramaEquipeEntityDomain);
        CronogramaEquipeEntityJpa cronogramaSalvo = cronogramaEquipeRepository.save(cronogramaJpa);
        return cronogramaEquipeMapper.toDomain(cronogramaSalvo);
    }
}
