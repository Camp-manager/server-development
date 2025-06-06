package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoBasicoDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarAcampamentoBasicoUC implements UseCase<Long, MethodResponse<AcampamentoBasicoDTO>> {

    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarAcampamentoBasicoUC(AcampamentoGateway acampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<AcampamentoBasicoDTO> execute(Long input) {
        boolean acampamentoEhExistentePorId = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!acampamentoEhExistentePorId) throw new EntityNotFoundException("Acampamento com id [" + input + "] não encontrado.");
        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input);
        return new MethodResponse<>(200, "Acampamento encontrado com sucesso!", new AcampamentoBasicoDTO(acampamentoEncontrado));
    }
}
