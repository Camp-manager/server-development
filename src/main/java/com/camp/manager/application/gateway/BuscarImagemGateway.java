package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.ImagemEntityDomain;

import java.util.List;

public interface BuscarImagemGateway {
    List<ImagemEntityDomain> buscarTodasImagens();
}
