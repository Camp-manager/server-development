package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.CronogramaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.LocalDateInvalidException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.cronograma.AlterarCronogramaRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AlterarCronogramaUC implements UseCase<AlterarCronogramaRequest, MethodResponse<Void>> {

    private final CronogramaGateway cronogramaGateway;

    @Autowired
    public AlterarCronogramaUC(CronogramaGateway cronogramaGateway) {
        this.cronogramaGateway = cronogramaGateway;
    }

    @Override
    public MethodResponse<Void> execute(AlterarCronogramaRequest input) {
        boolean cronogramaExistente = this.cronogramaGateway.cronogramaEhExistente(input.id());
        if(!cronogramaExistente) throw new NotFoundException("Cronograma  com o id: [" + input.id() + "] não encontrado!");

        CronogramaEntityDomain cronogramaAtual = this.cronogramaGateway.buscarCronogramaPorId(input.id());

        LocalDate dataInicio = LocalDateConverterAPP.converterStringParaLocalDate(input.dataInicial());
        LocalDate dataFinal = LocalDateConverterAPP.converterStringParaLocalDate(input.dataFinal());

        this.validarDatasInformadas(dataInicio, dataFinal);
        boolean houveAlteracao = this.houveAlteracao(input, cronogramaAtual);

        if(!houveAlteracao) {
            return new MethodResponse<>(304, "Nenhuma alteração foi realizada!", null);
        }

        this.realizarAlteracaoCronograma(input, cronogramaAtual, dataInicio, dataFinal);

        return new MethodResponse<>(202, "Cronograma adicionado com sucesso", null);
    }

    private void validarDatasInformadas(LocalDate dataInicio, LocalDate dataFinal) {
        if(dataFinal.isBefore(LocalDate.now())) { throw new LocalDateInvalidException("Data inicio não pode ser antes da data atual"); }
        if(dataInicio.isBefore(LocalDate.now())) { throw new LocalDateInvalidException("Data inicio não pode ser antes da data atual"); }
        if(dataFinal.isBefore(dataInicio)) { throw new LocalDateInvalidException("Data final não pode ser antes da data inicial"); }
    }

    private boolean houveAlteracao(AlterarCronogramaRequest input, CronogramaEntityDomain cronogramaAtual) {
        return !cronogramaAtual.dataInicio().equals(LocalDateConverterAPP.converterStringParaLocalDate(input.dataInicial())) ||
               !cronogramaAtual.dataFinal().equals(LocalDateConverterAPP.converterStringParaLocalDate(input.dataFinal())) ||
               !cronogramaAtual.descricao().equals(input.descricao());

    }

    private void realizarAlteracaoCronograma(AlterarCronogramaRequest input, CronogramaEntityDomain cronogramaAtual, LocalDate dataInicio, LocalDate dataFinal) {
        CronogramaEntityDomain cronogramaNovo = new CronogramaEntityDomain(
                cronogramaAtual.id(),
                dataInicio,
                dataFinal,
                input.descricao()
        );

        this.cronogramaGateway.adicionarCronograma(cronogramaNovo);
    }
}
