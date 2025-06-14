package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoBasicoDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarAcampamentosUC implements UseCase<Void, MethodResponse<List<AcampamentoBasicoDTO>>> {

    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarAcampamentosUC(AcampamentoGateway acampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<List<AcampamentoBasicoDTO>> execute(Void input) {
        List<AcampamentoEntityDomain> listaDeAcampamentos =  this.acampamentoGateway.buscarTodosOsAcampamentos();
        if(listaDeAcampamentos.isEmpty()) throw new NotFoundException("Não existem acampamentos cadastrados!");
        return new MethodResponse<>(200, "Acampamentos encontrados com sucesso!", AcampamentoBasicoDTO.converter(listaDeAcampamentos));
    }
}
