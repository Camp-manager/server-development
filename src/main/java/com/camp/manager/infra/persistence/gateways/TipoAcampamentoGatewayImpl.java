package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.TipoAcampamentoGateway;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.infra.mapper.TipoAcampamentoMapper;
import com.camp.manager.infra.persistence.entity.TipoAcampamentoEntityJpa;
import com.camp.manager.infra.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.TipoAcampamentoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TipoAcampamentoGatewayImpl implements TipoAcampamentoGateway {

    private final TipoAcampamentoRepository tipoAcampamentoRepository;
    private final TipoAcampamentoMapper tipoAcampamentoMapper;

    @Autowired
    public TipoAcampamentoGatewayImpl(TipoAcampamentoRepository tipoAcampamentoRepository,
                                      TipoAcampamentoMapper tipoAcampamentoMapper) {
        this.tipoAcampamentoRepository = tipoAcampamentoRepository;
        this.tipoAcampamentoMapper = tipoAcampamentoMapper;
    }

    @Override
    public List<TipoAcampamentoEntityDomain> buscarTodosTiposDeAcampamento() {
        return this.tipoAcampamentoRepository.findAll()
                .stream()
                .map(tipoAcampamentoMapper::toDomain)
                .toList();
    }

    @Override
    public boolean tipoAcampamentoEhExistentePorDescricao(String descricaoDoTipoAcampamento) {
        return this.tipoAcampamentoRepository.existsByDescricao(descricaoDoTipoAcampamento);
    }

    @Override
    public boolean tipoAcampamentoEhExistentePorId(Long idDoTipoAcampamento) {
        return this.tipoAcampamentoRepository.existsById(idDoTipoAcampamento);
    }

    @Override
    public void inserirTipoAcampamento(TipoAcampamentoEntityDomain tipoAcampamentoDomain) {
        this.tipoAcampamentoRepository.save(tipoAcampamentoMapper.toEntity(tipoAcampamentoDomain));
    }

    @Override
    public void deletarTipoAcampamentoPorId(Long idDoTipoAcampamento) {
        this.tipoAcampamentoRepository.deleteById(idDoTipoAcampamento);
    }

    @Override
    public TipoAcampamentoEntityDomain buscarTipoAcampamentoPorId(Long idDoTipoAcampamento) {
        return tipoAcampamentoMapper.toDomain(Objects.requireNonNull(this.tipoAcampamentoRepository.findById(idDoTipoAcampamento).orElse(null)));
    }
}
