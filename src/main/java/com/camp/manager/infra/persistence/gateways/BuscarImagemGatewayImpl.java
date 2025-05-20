package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.BuscarImagemGateway;
import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.domain.entity.UserEntityDomain;
import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import com.camp.manager.infra.persistence.mapper.ImagemMapper;
import com.camp.manager.infra.persistence.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarImagemGatewayImpl implements BuscarImagemGateway {

    private final ImagemRepository imagemRepository;

    @Autowired
    public BuscarImagemGatewayImpl(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }

    @Override
    public List<ImagemEntityDomain> buscarTodasImagens() {
        List<ImagemEntityJpa> todasAsImagens = this.imagemRepository.findAll();
        return todasAsImagens.stream()
                .map(ImagemMapper::toModel).collect(Collectors.toList());
    }
}
