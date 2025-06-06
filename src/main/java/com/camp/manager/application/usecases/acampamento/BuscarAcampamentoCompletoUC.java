package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoCompletoDTO;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarAcampamentoCompletoUC implements UseCase<Long, MethodResponse<AcampamentoCompletoDTO>> {

    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarAcampamentoCompletoUC(AcampamentoGateway acampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<AcampamentoCompletoDTO> execute(Long input) {
        boolean exists = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!exists) throw new EntityNotFoundException("Acampamento com id [" + input + "] não encontrado.");
        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input);
        return new MethodResponse<>(200, "Acampamento encontrado com sucesso!", new AcampamentoCompletoDTO(acampamentoEncontrado));
    }
}
