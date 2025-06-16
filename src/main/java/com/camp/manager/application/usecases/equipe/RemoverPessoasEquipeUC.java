package com.camp.manager.application.usecases.equipe;

import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.TipoEquipe;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.equipe.RemoverPessoasEquipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RemoverPessoasEquipeUC implements UseCase<RemoverPessoasEquipeRequest, MethodResponse<Void>> {

    private final EquipeGateway equipeGateway;

    @Autowired
    public RemoverPessoasEquipeUC(EquipeGateway equipeGateway) {
        this.equipeGateway = equipeGateway;
    }

    @Override
    public MethodResponse<Void> execute(RemoverPessoasEquipeRequest input) {
        boolean equipeExiste = this.equipeGateway.equipeEhExistentePorId(input.idEquipe());
        if (!equipeExiste) {
            throw new NotFoundException("Equipe com id [" + input.idEquipe() + "] não existe!");
        }

        this.equipeGateway.removerPessoasDaEquipe(input.idEquipe(), input.idsPessoas());

        return new MethodResponse<>(202, "Pessoas removidas da equipe com sucesso!", null);
    }
}
