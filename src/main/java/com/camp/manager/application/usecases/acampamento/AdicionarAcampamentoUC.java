package com.camp.manager.application.usecases.acampamento;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.gateway.TipoAcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.request.acampamento.CriarAcampamentoRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
public class AdicionarAcampamentoUC implements UseCase<CriarAcampamentoRequest, MethodResponse<Void>> {

    private final AcampamentoGateway acampamentoGateway;
    private final TemaGateway temaGateway;
    private final TipoAcampamentoGateway tipoAcampamentoGateway;

    @Autowired
    public AdicionarAcampamentoUC(AcampamentoGateway acampamentoGateway,
                                  TemaGateway temaGateway,
                                  TipoAcampamentoGateway tipoAcampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
        this.temaGateway = temaGateway;
        this.tipoAcampamentoGateway = tipoAcampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(CriarAcampamentoRequest input) {
        this.validarIdDeRelacionamento(input.idTema(), input.idTipoAcampamento());

        TipoAcampamentoEntityDomain tipoAcampamentoEncontrado =
                this.tipoAcampamentoGateway.buscarTipoAcampamentoPorId(input.idTipoAcampamento());

        TemaEntityDomain temaEncontrado =
                this.temaGateway.buscarTemaPorId(input.idTema());

        String codigoRegistro = this.gerarCodigoDeRegistroAcampamento();

        CronogramaEntityDomain cronogramaCriado = this.criarCronograma(input);

        AcampamentoEntityDomain acampamentoGerado = new AcampamentoEntityDomain(
                null,
                input.nomeDoAcampamento(),
                input.limiteCampistas(),
                input.limiteFuncionario(),
                codigoRegistro,
                temaEncontrado,
                tipoAcampamentoEncontrado,
                cronogramaCriado,
                new ArrayList<>(),
                new ArrayList<>()
        );

        this.acampamentoGateway.inserirAcampamento(acampamentoGerado);

        return new MethodResponse<>(201, "Acampamento adicionado com sucesso!", null);
    }

    private String gerarCodigoDeRegistroAcampamento() {
        return UUID.randomUUID().toString().substring(0, 6).toUpperCase();
    }

    private void validarIdDeRelacionamento(Long idTema, Long idTipoAcampamento) {
        boolean temaExiste = this.temaGateway.temaEhExistentePorId(idTema);
        boolean tipoAcampamentoExiste = this.tipoAcampamentoGateway.tipoAcampamentoEhExistentePorId(idTipoAcampamento);

        if(!temaExiste || !tipoAcampamentoExiste) {
            throw new EntityNotFoundException("Tema com id [" + idTema + "] ou Tipo de Acampamento com id [" + idTipoAcampamento + "] não encontrado!");
        }
    }

    private CronogramaEntityDomain criarCronograma(CriarAcampamentoRequest input) {
        return new CronogramaEntityDomain(
                null,
                LocalDateConverterAPP.converterStringParaLocalDate(input.cronogramaRequest().dataInicial()),
                LocalDateConverterAPP.converterStringParaLocalDate(input.cronogramaRequest().dataFinal()),
                "Cronograma do Acampamento: " + input.nomeDoAcampamento()
        );
    }
}
