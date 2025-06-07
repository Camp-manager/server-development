package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.infra.mapper.TemaMapper;
import com.camp.manager.infra.persistence.entity.TemaEntityJpa;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.TemaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemaGatewayImpl implements TemaGateway {

    private final TemaRepository temaRepository;
    private final TemaMapper temaMapper;

    @Autowired
    public TemaGatewayImpl(TemaRepository temaRepository,
                           TemaMapper temaMapper) {
        this.temaRepository = temaRepository;
        this.temaMapper = temaMapper;
    }

    @Override
    public boolean temaEhExistentePorDescricao(String descricaoDoTema) {
        return this.temaRepository.existsByDescricao(descricaoDoTema);
    }

    @Override
    public TemaEntityDomain inserirTema(TemaEntityDomain temaDomain) {
        return this.temaMapper.toDomain(this.temaRepository.save(this.temaMapper.toEntity(temaDomain)));
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
    @Transactional
    public void deletarTemaPorId(Long idTema) {
        this.temaRepository.deleteById(idTema);
    }

    @Override
    public void salvarTemaComImagem(TemaEntityDomain temaAtualizado) {
        this.temaRepository.save(this.temaMapper.toEntity(temaAtualizado));
    }
}
