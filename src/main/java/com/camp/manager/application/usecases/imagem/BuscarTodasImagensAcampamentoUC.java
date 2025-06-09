package com.camp.manager.application.usecases.imagem;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.ImagemEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodasImagensAcampamentoUC implements UseCase<Void, MethodResponse<Void>> {

    private final ImagemGateway imagemGateway;

    @Autowired
    public BuscarTodasImagensAcampamentoUC(ImagemGateway imagemGateway) {
        this.imagemGateway = imagemGateway;
    }

    public MethodResponse<Void> execute(Void input) {
        List<ImagemEntityDomain> todasAsImagens = this.imagemGateway.buscarTodasImagens();
        if(todasAsImagens.isEmpty()) throw new NotFoundException("Não existem imagens cadastradas!");




        return null;
    }
}
