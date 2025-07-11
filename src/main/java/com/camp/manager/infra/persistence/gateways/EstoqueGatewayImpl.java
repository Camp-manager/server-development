package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.EstoqueGateway;
import com.camp.manager.domain.entity.EstoqueEntityDomain;
import com.camp.manager.domain.enums.LocalEstoque;
import com.camp.manager.infra.mapper.EstoqueMapper;
import com.camp.manager.infra.persistence.entity.EstoqueEntityJpa;
import com.camp.manager.infra.persistence.repository.EstoqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class EstoqueGatewayImpl implements EstoqueGateway {

    private final EstoqueRepository estoqueRepository;
    private final EstoqueMapper estoqueMapper;

    @Autowired
    public EstoqueGatewayImpl(EstoqueRepository estoqueRepository,
                              EstoqueMapper estoqueMapper) {
        this.estoqueRepository = estoqueRepository;
        this.estoqueMapper = estoqueMapper;
    }

    @Override
    public List<EstoqueEntityDomain> buscarTodosOsEstoques() {
        return this.estoqueRepository.findAll()
                .stream()
                .map(this.estoqueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<EstoqueEntityDomain> buscarEstoquesPorTipoEstoque(String tipoEstoque) {
        return this.estoqueRepository.findAllByLocalEstoque(LocalEstoque.fromDescricao(tipoEstoque))
                .stream()
                .map(this.estoqueMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public EstoqueEntityDomain buscarEstoquePorId(Long idEstoque) {
        return this.estoqueMapper.toDomain(Objects.requireNonNull(this.estoqueRepository.findById(idEstoque).orElse(null)));
    }

    @Override
    public boolean estoqueEhExistente(Long idEstoque) {
        return this.estoqueRepository.existsById(idEstoque);
    }

    @Override
    public void salvarEstoque(EstoqueEntityDomain estoqueEntityDomain) {
        EstoqueEntityJpa teste = this.estoqueMapper.toEntity(estoqueEntityDomain);
        teste.getItens().forEach(item -> {
                    item.setEstoque(teste);
                });
       this.estoqueRepository.save(teste);
    }
}
