package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.ImagemEntityDomain;

import java.util.List;


public interface ImagemGateway {
    void inserirNovaImagem(String path, String data, AcampamentoEntityDomain acampamentoEncontrado);
    List<ImagemEntityDomain> buscarTodasImagens();
}
