package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.pessoa.CriarCampistaRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarCampistaUC implements UseCase<CriarCampistaRequest, MethodResponse<Void>>{

    private final CampistaGateway campistaGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AdicionarCampistaUC(CampistaGateway campistaGateway,
                               AcampamentoGateway acampamentoGateway) {
        this.campistaGateway = campistaGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarCampistaRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorCodigoRegistro(input.getCodigoRegistro());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com código de registro [" + input.getCodigoRegistro() + "] não existe!");

        return null;
    }
}
