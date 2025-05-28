package com.camp.manager.application.usecases.imagem;

import com.camp.manager.application.gateway.ImagemGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTodasImagensAcampamentoUC implements UseCase<Void, MethodResponse<List<byte[]>>> {

    private final ImagemGateway imagemGateway;

    @Autowired
    public BuscarTodasImagensAcampamentoUC(ImagemGateway imagemGateway) {
        this.imagemGateway = imagemGateway;
    }

    public MethodResponse<List<byte[]>> execute(Void input) {

        return null;
    }
}
