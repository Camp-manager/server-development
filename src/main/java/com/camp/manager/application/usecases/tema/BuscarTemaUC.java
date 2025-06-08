package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.tema.TemaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarTemaUC implements UseCase<Long, MethodResponse<TemaDTO>> {

    private final TemaGateway temaGateway;

    @Autowired
    public BuscarTemaUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    public MethodResponse<TemaDTO> execute(Long input) {
        boolean temaEhExistente = this.temaGateway.temaEhExistentePorId(input);
        if(!temaEhExistente) throw new NotFoundException("Tema com o id [" + input + "] não encontrado!");

        TemaEntityDomain temaEntityDomain = this.temaGateway.buscarTemaPorId(input);

        return new MethodResponse<>(200, "Tema encontrado com sucesso!", new TemaDTO(temaEntityDomain));
    }
}
