package com.camp.manager.application.usecases.tipoacampamento;

import com.camp.manager.application.gateway.TipoAcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TipoAcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.infra.http.request.tipoacampamento.CriarTipoAcampamentoRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdicionarTipoAcampamentoUC implements UseCase<CriarTipoAcampamentoRequest, MethodResponse<Void>> {

    private final TipoAcampamentoGateway tipoAcampamentoGateway;

    @Autowired
    public AdicionarTipoAcampamentoUC(TipoAcampamentoGateway tipoAcampamentoGateway) {
        this.tipoAcampamentoGateway = tipoAcampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(CriarTipoAcampamentoRequest input) {
        boolean tipoAcampamentoExistente = tipoAcampamentoGateway.tipoAcampamentoEhExistentePorDescricao(input.descricao());
        if (tipoAcampamentoExistente) throw new EntityFoundException("Tipo de acampamento já cadastrado com essa descrição!");

        TipoAcampamentoEntityDomain tipoAcampamento = new TipoAcampamentoEntityDomain(null, input.descricao(), input.categoriaDoAcampamento());

        this.tipoAcampamentoGateway.inserirTipoAcampamento(tipoAcampamento);

        return new MethodResponse<>(201, "Tipo de acampamento cadastrado com sucesso!", null);
    }
}
