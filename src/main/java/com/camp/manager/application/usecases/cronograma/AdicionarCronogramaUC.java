package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.CronogramaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.LocalDateInvalidException;
import com.camp.manager.infra.http.request.cronograma.CriarCronogramaRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class AdicionarCronogramaUC implements UseCase<CriarCronogramaRequest, MethodResponse<Void>> {

    private final CronogramaGateway cronogramaGateway;

    @Autowired
    public AdicionarCronogramaUC(CronogramaGateway cronogramaGateway) {
        this.cronogramaGateway = cronogramaGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarCronogramaRequest input) {
        LocalDate dataInicio = LocalDateConverterAPP.converterStringParaLocalDate(input.dataInicial());
        LocalDate dataFinal = LocalDateConverterAPP.converterStringParaLocalDate(input.dataFinal());

        this.validarDatasInformadas(dataInicio, dataFinal);

        CronogramaEntityDomain cronogramaNovo = new CronogramaEntityDomain(
                null,
                dataInicio,
                dataFinal,
                input.descricao()
        );

        this.cronogramaGateway.adicionarCronograma(cronogramaNovo);

        return new MethodResponse<>(201, "Cronograma adicionado com sucesso", null);
    }

    private void validarDatasInformadas(LocalDate dataInicio, LocalDate dataFinal) {
        if(dataFinal.isBefore(LocalDate.now())) { throw new LocalDateInvalidException("Data inicio não pode ser antes da data atual"); }
        if(dataInicio.isBefore(LocalDate.now())) { throw new LocalDateInvalidException("Data inicio não pode ser antes da data atual"); }
        if(dataFinal.isBefore(dataInicio)) { throw new LocalDateInvalidException("Data final não pode ser antes da data inicial"); }
    }
}
