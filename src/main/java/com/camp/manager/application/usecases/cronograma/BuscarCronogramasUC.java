package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.CronogramaGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.cronograma.TodosCronogramaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BuscarCronogramasUC implements UseCase<Long, MethodResponse<TodosCronogramaDTO>> {

    private final CronogramaGateway cronogramaGateway;
    private final AcampamentoGateway acampamentoGateway;
    private final EquipeGateway equipeGateway;

    @Autowired
    public BuscarCronogramasUC(CronogramaGateway cronogramaGateway,
                               AcampamentoGateway acampamentoGateway,
                               EquipeGateway equipeGateway) {
        this.cronogramaGateway = cronogramaGateway;
        this.acampamentoGateway = acampamentoGateway;
        this.equipeGateway = equipeGateway;
    }

    @Override
    public MethodResponse<TodosCronogramaDTO> execute(Long input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!acampamentoEhExistente) throw new NotFoundException("Acampamento com o id: [" + input + "] não encontrado!");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input);



        return null;
    }
}
