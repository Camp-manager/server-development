package com.camp.manager.application.usecases.equipe;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.equipe.EquipeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarEquipesUC implements UseCase<Long, MethodResponse<List<EquipeDTO>>> {

    private final EquipeGateway equipeGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarEquipesUC(EquipeGateway equipeGateway,
                           AcampamentoGateway acampamentoGateway) {
        this.equipeGateway = equipeGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<List<EquipeDTO>> execute(Long input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.existsAcampamentoById(input);
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com id [" + input + "] não existe!");

        List<EquipeEntityDomain> listaDeEquipes = this.equipeGateway.buscarTodasEquipesDeTrabalho(input);
        return new MethodResponse<>(200, "Equipes encontradas com sucesso!", EquipeDTO.converter(listaDeEquipes));
    }
}
