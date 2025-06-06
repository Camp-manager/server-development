package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.infra.mapper.EquipeMapper;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.entity.EquipeEntityJpa;
import com.camp.manager.infra.persistence.repository.EquipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipeGatewayImpl implements EquipeGateway {

    private final EquipeRepository equipeRepository;
    private final Mapper<EquipeEntityJpa, EquipeEntityDomain> equipeMapper;

    @Autowired
    public EquipeGatewayImpl(EquipeRepository equipeRepository,
                             Mapper<EquipeEntityJpa, EquipeEntityDomain> equipeMapper) {
        this.equipeRepository = equipeRepository;
        this.equipeMapper = equipeMapper;
    }

    @Override
    public void inserirTodosAsEquipesDeTrabalho(List<EquipeEntityDomain> equipesDeTrabalho) {
        equipesDeTrabalho.forEach(equipe -> {
            EquipeEntityJpa equipeEntityJpa = this.equipeMapper.toEntity(equipe);
            this.equipeRepository.saveAndFlush(equipeEntityJpa);
        });
    }

    @Override
    public List<EquipeEntityDomain> buscarTodasEquipesDeTrabalho(Long idAcampamento) {
        return this.equipeRepository.findByAcampamento_Id(idAcampamento)
                .stream()
                .map(equipeMapper::toDomain)
                .toList();
    }

    @Override
    public boolean equipeEhExistente(Long idEquipe) {
        return this.equipeRepository.existsById(idEquipe);
    }

    @Override
    public void salvarEquipe(EquipeEntityDomain equipeEntityDomain) {
        this.equipeRepository.saveAndFlush(this.equipeMapper.toEntity(equipeEntityDomain));
    }

    @Override
    public EquipeEntityDomain buscarEquipePorId(Long idEquipe) {
        return this.equipeMapper.toDomain(this.equipeRepository.findById(idEquipe).orElse(null));
    }
}
