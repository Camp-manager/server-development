package com.camp.manager.infra.persistence.gateways;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.infra.persistence.repository.ImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImagemGatewayImpl implements ImagemGateway {

    private final ImagemRepository imagemRepository;

    @Autowired
    public ImagemGatewayImpl(ImagemRepository imagemRepository) {
        this.imagemRepository = imagemRepository;
    }


    @Override
    public void inserirNovaImagem(AcampamentoEntityDomain acampamentoEntityDomain, byte[] bytesImagem) {

    }
}
