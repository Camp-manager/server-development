package com.camp.manager.application.usecases.tipoacampamento;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoAcampanentoUC implements UseCase<Long, MethodResponse<Void>> {


    @Override
    public MethodResponse<Void> execute(Long input) {
        return null;
    }
}
