package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.tema.TemaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTemasUC implements UseCase<MethodResponse<Void>, List<TemaDTO>> {

    private final TemaGateway temaGateway;

    @Autowired
    public BuscarTemasUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    public List<TemaDTO> execute(MethodResponse<Void> input) {
        List<TemaEntityDomain> listaDeTemas = this.temaGateway.buscarTodosOsTemas();
        if(listaDeTemas.isEmpty()) throw new NotFoundException("Não existem temas cadastrados!");
        return TemaDTO.converter(listaDeTemas);
    }
}
