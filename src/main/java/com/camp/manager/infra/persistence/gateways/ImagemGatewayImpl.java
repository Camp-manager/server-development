package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.infra.persistence.entity.AcampamentoEntityJpa;
import com.camp.manager.infra.persistence.entity.ImagemEntityJpa;
import com.camp.manager.infra.persistence.mapper.Mapper;
import com.camp.manager.infra.persistence.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ImagemGatewayImpl implements ImagemGateway {

    private final ImagemRepository imagemRepository;
    private final Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapper;

    @Autowired
    public ImagemGatewayImpl(ImagemRepository imagemRepository, Mapper<AcampamentoEntityJpa, AcampamentoEntityDomain> mapper) {
        this.imagemRepository = imagemRepository;
        this.mapper = mapper;
    }

    @Override
    public void inserirNovaImagem(String path, String data, AcampamentoEntityDomain acampamentoEncontrado) {
        AcampamentoEntityJpa acampamentoEntityJpa = mapper.toEntity(acampamentoEncontrado);
        this.imagemRepository.save(new ImagemEntityJpa(null, path, data, acampamentoEntityJpa));
    }

}
