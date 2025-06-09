package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.mapper.AcampamentoMapper;
import com.camp.manager.infra.persistence.repository.AcampamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AcampamentoGatewayImpl implements AcampamentoGateway {

    private final AcampamentoRepository acampamentoRepository;
    private final AcampamentoMapper mapper;

    @Autowired
    public AcampamentoGatewayImpl(AcampamentoRepository acampamentoRepository,
                                  AcampamentoMapper mapper) {
        this.acampamentoRepository = acampamentoRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean acampamentoEhExistentePorId(Long idAcampamento) {
        return this.acampamentoRepository.existsById(idAcampamento);
    }

    @Override
    public AcampamentoEntityDomain buscarAcampamentoPorId(Long idAcampamento) {
        return mapper.toDomain(this.acampamentoRepository.findById(idAcampamento)
                .orElseThrow(() -> new NotFoundException("Acampamento com id ["+idAcampamento+"] não encontrado!")));
    }

    @Override
    public void inserirAcampamento(AcampamentoEntityDomain acampamentoGerado) {
        this.acampamentoRepository.saveAndFlush(mapper.toEntity(acampamentoGerado));
    }

    @Override
    public List<AcampamentoEntityDomain> buscarTodosOsAcampamentos() {
        return this.acampamentoRepository.findAll()
                .stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public boolean acampamentoEhExistentePorCodigoRegistro(String codigoRegistro) {
        return this.acampamentoRepository.existsByCodigoRegistro(codigoRegistro);
    }

    @Override
    public AcampamentoEntityDomain buscarAcampamentoPorCodigoRegistro(String codigoRegistro) {
        return mapper.toDomain(this.acampamentoRepository.findByCodigoRegistro(codigoRegistro));
    }
}
