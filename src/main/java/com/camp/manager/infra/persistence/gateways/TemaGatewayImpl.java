package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import com.camp.manager.infra.persistence.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.TemaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemaGatewayImpl implements TemaGateway {

    private final TemaRepository temaRepository;
    private final Mapper<TemaEntityJpa, TemaEntityDomain> temaMapper;

    @Autowired
    public TemaGatewayImpl(TemaRepository temaRepository, Mapper<TemaEntityJpa, TemaEntityDomain> temaMapper) {
        this.temaRepository = temaRepository;
        this.temaMapper = temaMapper;
    }

    @Override
    public boolean temaEhExistentePorDescricao(String descricaoDoTema) {
        return this.temaRepository.existsByDescricao(descricaoDoTema);
    }

    @Override
    public void inserirTema(TemaEntityDomain temaDomain) {
        this.temaRepository.save(temaMapper.toEntity(temaDomain));
    }

    @Override
    public boolean temaEhExistentePorId(Long idTema) {
        return this.temaRepository.existsById(idTema);
    }

    @Override
    public TemaEntityDomain buscarTemaPorId(Long idTema) {
        return this.temaRepository.findById(idTema)
                .map(temaMapper::toDomain)
                .orElse(null);
    }

    @Override
    public void atualizarTema(TemaEntityDomain temaDomain) {
        this.temaRepository.save(temaMapper.toEntity(temaDomain));
    }

    @Override
    public List<TemaEntityDomain> buscarTodosOsTemas() {
        return this.temaRepository.findAll()
                .stream()
                .map(temaMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deletarTemaPorId(Long idTema) {
        this.temaRepository.deleteById(idTema);
    }
}
