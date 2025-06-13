package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.acampamento.AcampamentoBasicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@Service
public class BuscarProximoAcampamentoUC implements UseCase<Void, MethodResponse<AcampamentoBasicoDTO>> {

    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarProximoAcampamentoUC(AcampamentoGateway acampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<AcampamentoBasicoDTO> execute(Void input) {
        List<AcampamentoEntityDomain> acampamentosEncontrados = this.acampamentoGateway.buscarTodosOsAcampamentos();
        if(acampamentosEncontrados.isEmpty()) throw new NotFoundException("Não existem acampamentos cadastrados!");

        AcampamentoEntityDomain acampamentoMaisProximo = acampamentosEncontrados.stream()
                .filter(acampamento -> acampamento.cronograma().dataInicio() != null
                        && acampamento.cronograma().dataFinal() != null
                        && acampamento.cronograma().dataInicio().isAfter(LocalDate.now()))
                .min(Comparator.comparing(AcampamentoEntityDomain::cronograma, Comparator.comparing(CronogramaEntityDomain::dataInicio)))
                .orElse(null);

        assert acampamentoMaisProximo != null;
        return new MethodResponse<>(200, "Acampamento encontrado com sucesso!", new AcampamentoBasicoDTO(acampamentoMaisProximo));
    }
}
