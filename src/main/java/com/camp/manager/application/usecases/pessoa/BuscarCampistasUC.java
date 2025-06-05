package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.pessoa.CampistaBasicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarCampistasUC implements UseCase<Long, MethodResponse<List<CampistaBasicoDTO>>> {

    private final CampistaGateway campistaGateway;

    @Autowired
    public BuscarCampistasUC(CampistaGateway campistaGateway) {
        this.campistaGateway = campistaGateway;
    }

    @Override
    public MethodResponse<List<CampistaBasicoDTO>> execute(Long input) {
        return null;
    }
}
