package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.CronogramaGateway;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.infra.mapper.CronogramaMapper;
import com.camp.manager.infra.persistence.repository.CronogramaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CronogramaGatewayImpl implements CronogramaGateway {

    private final CronogramaRepository cronogramaRepository;
    private final CronogramaMapper cronogramaMapper;

    @Autowired
    public CronogramaGatewayImpl(CronogramaRepository cronogramaRepository,
                                 CronogramaMapper cronogramaMapper) {
        this.cronogramaRepository = cronogramaRepository;
        this.cronogramaMapper = cronogramaMapper;
    }

    @Override
    public void adicionarCronograma(CronogramaEntityDomain cronograma) {
        this.cronogramaRepository.saveAndFlush(this.cronogramaMapper.toEntity(cronograma));
    }

    @Override
    public CronogramaEntityDomain buscarCronogramaPorId(Long id) {
        return this.cronogramaRepository.findById(id)
                .map(cronogramaMapper::toDomain)
                .orElse(null);
    }

    @Override
    public boolean cronogramaEhExistente(Long idCronograma) {
        return this.cronogramaRepository.existsById(idCronograma);
    }

    @Override
    public List<CronogramaEntityDomain> buscarTodosCronogramasPorAcampamento() {
        return List.of();
    }
}
