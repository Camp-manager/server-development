package com.camp.manager.application.usecases.equipe;

import com.camp.manager.application.gateway.CampistaGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.CampistaEntityDomain;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
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
        boolean acampamentoEhExistente = this.equipeGateway.equipeEhExistente(input.idEquipe());
        if (!acampamentoEhExistente) throw new NotFoundException("Equipe com id [" + input.idEquipe() + "] não existe!");

        EquipeEntityDomain equipeEncontrada = this.equipeGateway.buscarEquipePorId(input.idEquipe());

        EquipeEntityDomain equipeAtualizada = this.removerPessoasDasEquipes(input, equipeEncontrada);

        this.equipeGateway.salvarEquipe(equipeAtualizada);

        return new MethodResponse<>(202, "Pessoas removidas da equipe com sucesso!", null);
    }

    private EquipeEntityDomain removerPessoasDasEquipes(RemoverPessoasEquipeRequest request, EquipeEntityDomain equipeEncontrada) {
        List<FuncionarioEntityDomain> funcionariosAtuais = new ArrayList<>(equipeEncontrada.funcionariosNaEquipe());
        List<CampistaEntityDomain> campistasAtuais = new ArrayList<>(equipeEncontrada.campistasNaEquipe());

        if (equipeEncontrada.tipoEquipe().equals("CAMPISTA")) {
            campistasAtuais.removeIf(campista -> request.idsPessoas().contains(campista.id()));
        } else if (equipeEncontrada.tipoEquipe().equals("FUNCIONARIO")) {
            funcionariosAtuais.removeIf(funcionario -> request.idsPessoas().contains(funcionario.id()));
        }

        return new EquipeEntityDomain(
                equipeEncontrada.id(),
                equipeEncontrada.nome(),
                equipeEncontrada.tipoEquipe(),
                equipeEncontrada.cronograma(),
                equipeEncontrada.acampamento(),
                campistasAtuais,
                funcionariosAtuais,
                equipeEncontrada.liderDaEquipe()
        );
    }
}
