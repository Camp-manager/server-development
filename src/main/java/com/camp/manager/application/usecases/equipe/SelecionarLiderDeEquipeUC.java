package com.camp.manager.application.usecases.equipe;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.gateway.FuncionarioGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.EntityFoundException;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.equipe.LiderEquipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SelecionarLiderDeEquipeUC implements UseCase<LiderEquipeRequest, MethodResponse<Void>> {

    private final EquipeGateway equipeGateway;
    private final AcampamentoGateway acampamentoGateway;
    private final FuncionarioGateway funcionarioGateway;

    @Autowired
    public SelecionarLiderDeEquipeUC(EquipeGateway equipeGateway,
                                     AcampamentoGateway acampamentoGateway,
                                     FuncionarioGateway funcionarioGateway) {
        this.equipeGateway = equipeGateway;
        this.acampamentoGateway = acampamentoGateway;
        this.funcionarioGateway = funcionarioGateway;
    }

    @Override
    public MethodResponse<Void> execute(LiderEquipeRequest input) {
        this.validarExistenciaDeIds(input);

        EquipeEntityDomain equipeEncontrada = this.equipeGateway.buscarEquipePorId(input.idEquipe());
        if(equipeEncontrada == null) throw new EntityFoundException("Equipe com o Id: [" + input.idEquipe() + "] não existe!");

        if(equipeEncontrada.liderDaEquipe() != null) throw new EntityFoundException("Equipe com o Id: [" + input.idEquipe() + "] já possui um líder!");

        EquipeEntityDomain equipeAtualizada = new EquipeEntityDomain(
                equipeEncontrada.id(),
                equipeEncontrada.nome(),
                equipeEncontrada.tipoEquipe(),
                equipeEncontrada.cronograma(),
                equipeEncontrada.acampamento(),
                equipeEncontrada.campistasNaEquipe(),
                equipeEncontrada.funcionariosNaEquipe(),
                this.funcionarioGateway.buscarFuncionarioPorId(input.idFuncionario()),
                equipeEncontrada.diasDaFuncao()
        );

        this.equipeGateway.salvarEquipe(equipeAtualizada);

        return new MethodResponse<>(202, "Líder da equipe adicionado com sucesso!", null);
    }

    private void validarExistenciaDeIds(LiderEquipeRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input.idAcampamento());
        if(!acampamentoEhExistente) throw new NotFoundException("Acampamento com o Id: [" + input.idAcampamento() + "] não existe!");

        boolean funcionarioEhExistente = this.funcionarioGateway.funcionarioEhExistentePorId(input.idFuncionario());
        if(!funcionarioEhExistente) throw new NotFoundException("Funcionário com o Id: [" + input.idFuncionario() + "] não existe!");

        boolean equipeEhExistente = this.equipeGateway.equipeEhExistentePorId(input.idEquipe());
        if(!equipeEhExistente) throw new NotFoundException("Equipe com o Id: [" + input.idEquipe() + "] não existe!");
    }
}
