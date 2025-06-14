package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.TemaEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.infra.http.request.tema.CriarTemaRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AdicionarTemaUC implements UseCase<CriarTemaRequest, MethodResponse<Long>> {

    private final TemaGateway temaGateway;

    @Autowired
    public AdicionarTemaUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Long> execute(CriarTemaRequest input) {
        boolean temaEhExistente = this.temaGateway.temaEhExistentePorDescricao(input.descricao());
        if(temaEhExistente) {throw new EntityFoundException("Tema já cadastrado!");}

        TemaEntityDomain temaCadastrado = this.temaGateway.inserirTema(
                new TemaEntityDomain(null, input.descricao(), null, BigDecimal.valueOf(input.precoCamiseta()), BigDecimal.valueOf(input.precoAcampamento())));

        return new MethodResponse<>(201, "Tema cadastrado com sucesso!", temaCadastrado.id());
    }



}
