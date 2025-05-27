package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import org.springframework.stereotype.Service;

@Service
public class AdicionarAcampamentoUC implements UseCase<Void, MethodResponse<Void>> {
    @Override
    public MethodResponse<Void> execute(Void input) {
        return null;
    }
}
