package com.camp.manager.application.usecases.tipoacampamento;

import com.camp.manager.application.gateway.TipoAcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.tipoacampamento.TipoAcampamentoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarTipoAcampamentosUC implements UseCase<MethodResponse<Void>, MethodResponse<List<TipoAcampamentoDTO>>> {

    private final TipoAcampamentoGateway tipoAcampamentoGateway;

    @Autowired
    public BuscarTipoAcampamentosUC(TipoAcampamentoGateway tipoAcampamentoGateway) {
        this.tipoAcampamentoGateway = tipoAcampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<List<TipoAcampamentoDTO>> execute(MethodResponse<Void> input) {
        List<TipoAcampamentoEntityDomain> listaDeTiposDeAcampamento = this.tipoAcampamentoGateway.buscarTodosTiposDeAcampamento();
        if(listaDeTiposDeAcampamento.isEmpty()) throw new NotFoundException("Não existem tipos de acampamento cadastrados!");
        return new MethodResponse<>(200, "Tipos de acampamento encontrados com sucesso!", TipoAcampamentoDTO.converter(listaDeTiposDeAcampamento));
    }
}
