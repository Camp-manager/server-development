package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.mapper.EquipeMapper;
import com.camp.manager.infra.persistence.entity.EquipeEntityJpa;
import com.camp.manager.infra.persistence.repository.EquipeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EquipeGatewayImpl implements EquipeGateway {

    private final EquipeRepository equipeRepository;
    private final EquipeMapper equipeMapper;

    @Autowired
    public EquipeGatewayImpl(EquipeRepository equipeRepository,
                             EquipeMapper equipeMapper) {
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
        return this.equipeRepository.findAllByAcampamento_Id(idAcampamento)
                .stream()
                .map(equipeMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean equipeEhExistentePorId(Long idEquipe) {
        return this.equipeRepository.existsById(idEquipe);
    }

    @Override
    public void salvarEquipe(EquipeEntityDomain equipeEntityDomain) {
        this.equipeRepository.save  (this.equipeMapper.toEntity(equipeEntityDomain));
    }

    @Override
    public EquipeEntityDomain buscarEquipePorId(Long idEquipe) {
        return this.equipeMapper.toDomain(Objects.requireNonNull(this.equipeRepository.findById(idEquipe).orElse(null)));
    }

    @Override
    @Transactional
    public void removerPessoasDaEquipe(Long aLong, List<Long> longs) {
        EquipeEntityJpa equipeGerida = this.equipeRepository.findById(aLong)
                .orElseThrow(() -> new NotFoundException("Equipe com id [" + aLong + "] não existe!"));

        TipoEquipe tipoEquipe = equipeGerida.getTipoEquipe();

        if (tipoEquipe == TipoEquipe.CAMPISTA) {
            equipeGerida.getCampistas().removeIf(campista -> longs.contains(campista.getId()));
        } else if (tipoEquipe == TipoEquipe.TRABALHO) {
            equipeGerida.getFuncionarios().removeIf(funcionario -> longs.contains(funcionario.getId()));
        }
    }
}
