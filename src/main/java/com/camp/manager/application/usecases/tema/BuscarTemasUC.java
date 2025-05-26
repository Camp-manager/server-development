package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarTemasUC implements UseCase<MethodResponse<Void>, Void> {

    private final TemaGateway temaGateway;

    @Autowired
    public BuscarTemasUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    public Void execute(MethodResponse<Void> input) {
        return null;
    }
}
