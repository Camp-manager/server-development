package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.infra.mapper.AcampamentoMapper;
import com.camp.manager.infra.mapper.ImagemMapper;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import com.camp.manager.infra.persistence.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagemGatewayImpl implements ImagemGateway {

    private final ImagemRepository imagemRepository;
    private final AcampamentoMapper acampamentoMapper;
    private final ImagemMapper imagemMapper;

    @Autowired
    public ImagemGatewayImpl(ImagemRepository imagemRepository,
                             AcampamentoMapper mapper,
                             ImagemMapper imagemMapper) {
        this.imagemRepository = imagemRepository;
        this.acampamentoMapper = mapper;
        this.imagemMapper = imagemMapper;
    }

    @Override
    public void inserirNovaImagem(String path, String data, AcampamentoEntityDomain acampamentoEncontrado) {
        AcampamentoEntityJpa acampamentoEntityJpa = acampamentoMapper.toEntity(acampamentoEncontrado);
        this.imagemRepository
                .save(new ImagemEntityJpa(null, path, data, acampamentoEntityJpa));
    }

    @Override
    public List<ImagemEntityDomain> buscarTodasImagens() {
        return this.imagemRepository
                .findAll()
                .stream()
                .map(imagemMapper::toDomain)
                .collect(Collectors.toList());
    }

}
