package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.CronogramaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.request.cronograma.EstenderCronogramaRequest;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstenderCronogramaUC implements UseCase<EstenderCronogramaRequest, MethodResponse<Void>> {

    private final CronogramaGateway cronogramaGateway;

    @Autowired
    public EstenderCronogramaUC(CronogramaGateway cronogramaGateway) {
        this.cronogramaGateway = cronogramaGateway;
    }

    @Override
    public MethodResponse<Void> execute(EstenderCronogramaRequest input) {
        boolean cronogramaEhExistente = this.cronogramaGateway.cronogramaEhExistente(input.idCronograma());
        if (!cronogramaEhExistente) throw new EntityNotFoundException("Cronograma com o id [" + input.idCronograma() + "] não encontrado!");

        CronogramaEntityDomain cronogramaDomain = this.cronogramaGateway.buscarCronogramaPorId(input.idCronograma());

        CronogramaEntityDomain cronogramaDomainAtualizado = new CronogramaEntityDomain(
                cronogramaDomain.id(),
                cronogramaDomain.dataInicio(),
                cronogramaDomain.dataFinal().plusDays(input.diasParaEstender()),
                cronogramaDomain.descricao()
        );

        this.cronogramaGateway.adicionarCronograma(cronogramaDomainAtualizado);

        return new MethodResponse<>(202, "Cronograma estendido com sucesso!", null);
    }
}
