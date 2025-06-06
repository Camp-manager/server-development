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
import com.camp.manager.infra.http.request.equipe.AdicionarPessoasEquipeRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdicionarPessoasEquipeUC implements UseCase<AdicionarPessoasEquipeRequest, MethodResponse<Void>> {

    private final EquipeGateway equipeGateway;
    private final CampistaGateway campistaGateway;
    private final FuncionarioGateway funcionarioGateway;

    @Autowired
    public AdicionarPessoasEquipeUC(EquipeGateway equipeGateway,
                                    CampistaGateway campistaGateway,
                                    FuncionarioGateway funcionarioGateway) {
        this.equipeGateway = equipeGateway;
        this.campistaGateway = campistaGateway;
        this.funcionarioGateway = funcionarioGateway;
    }

    @Override
    public MethodResponse<Void> execute(AdicionarPessoasEquipeRequest input) {
        boolean equipeEhExistente = this.equipeGateway.equipeEhExistente(input.idEquipe());
        if(!equipeEhExistente) throw new NotFoundException( "Equipe com id [" + input.idEquipe() + "] não existe!");

        EquipeEntityDomain equipeEncontrada = this.equipeGateway.buscarEquipePorId(input.idEquipe());

        List<Object> pessoasParaAdicionar = this.buscarCampistasEFuncionarios(input.idsPessoas(), equipeEncontrada);

        this.adicionarPessoasNaEquipe(equipeEncontrada, pessoasParaAdicionar);

        return null;
    }

    private List<Object> buscarCampistasEFuncionarios(List<Long> idsPessoas, EquipeEntityDomain equipeEncontrada) {
        List<Object> pessoasParaAdicionar = new ArrayList<>();

        if(equipeEncontrada.tipoEquipe().equals("CAMPISTA")) {
            idsPessoas.forEach(id -> {
                boolean campistaEhExistente = this.campistaGateway.campistaEhExistentePorId(id);
                if(!campistaEhExistente) throw new NotFoundException("Campista com id [" + id + "] não existe!");
                CampistaEntityDomain campistaEncontrado = this.campistaGateway.buscarCampistaPorId(id);
                pessoasParaAdicionar.add(campistaEncontrado);
            });
        } else {
            idsPessoas.forEach(id -> {
                boolean funcionarioEhExistente = this.funcionarioGateway.funcionarioEhExistentePorId(id);
                if(!funcionarioEhExistente) throw new NotFoundException("Funcionário com id [" + id + "] não existe!");
                FuncionarioEntityDomain funcionarioEncontrado = this.funcionarioGateway.buscarFuncionarioPorId(id);
                pessoasParaAdicionar.add(funcionarioEncontrado);
            });
        }
        return pessoasParaAdicionar;
    }

    private void adicionarPessoasNaEquipe(EquipeEntityDomain equipeEncontrada, List<Object> pessoasParaAdicionar) {
        if(equipeEncontrada.tipoEquipe().equals("CAMPISTA")) {

        } else {
            List<FuncionarioEntityDomain> funcionarios = new ArrayList<>();
            pessoasParaAdicionar.forEach(pessoa -> funcionarios.add((FuncionarioEntityDomain) pessoa));
        }
    }
}
