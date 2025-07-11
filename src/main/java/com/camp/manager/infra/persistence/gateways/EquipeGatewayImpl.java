package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.mapper.EquipeMapper;
import com.camp.manager.infra.persistence.entity.CampistaEntityJpa;
import com.camp.manager.infra.persistence.entity.EquipeEntityJpa;
import com.camp.manager.infra.persistence.entity.FuncionarioEntityJpa;
import com.camp.manager.infra.persistence.repository.CampistaRepository;
import com.camp.manager.infra.persistence.repository.EquipeRepository;
import com.camp.manager.infra.persistence.repository.FuncionarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EquipeGatewayImpl implements EquipeGateway {

    private final EquipeRepository equipeRepository;
    private final CampistaRepository campistaRepository;
    private final FuncionarioRepository funcionarioRepository;
    private final EquipeMapper equipeMapper;

    @Autowired
    public EquipeGatewayImpl(EquipeRepository equipeRepository,
                             CampistaRepository campistaRepository,
                             FuncionarioRepository funcionarioRepository,
                             EquipeMapper equipeMapper) {
        this.equipeRepository = equipeRepository;
        this.campistaRepository = campistaRepository;
        this.funcionarioRepository = funcionarioRepository;
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
        this.equipeRepository.save (this.equipeMapper.toEntity(equipeEntityDomain));
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
            longs.forEach(id -> {
                CampistaEntityJpa campista = this.campistaRepository.findById(id).orElse(null);
                assert campista != null;
                campista.setEquipe(null);
                this.campistaRepository.save(campista);
            });
        } else if (tipoEquipe == TipoEquipe.TRABALHO) {
            longs.forEach(id -> {
                FuncionarioEntityJpa funcionario = this.funcionarioRepository.findById(id).orElse(null);
                assert funcionario != null;
                funcionario.setEquipe(null);
                this.funcionarioRepository.save(funcionario);
            });
        }

        this.equipeRepository.save(equipeGerida);
    }
}
