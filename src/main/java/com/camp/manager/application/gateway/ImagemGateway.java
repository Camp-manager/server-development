package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.AcampamentoEntityDomain;


public interface ImagemGateway {
    void inserirNovaImagem(String path, String data, AcampamentoEntityDomain acampamentoEncontrado);
}
