package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.request.acampamento.CriarAcampamentoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarAcampamentoUC implements UseCase<CriarAcampamentoRequest, MethodResponse<Void>> {

    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AdicionarAcampamentoUC(AcampamentoGateway acampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarAcampamentoRequest input) {
        return null;
    }
}
