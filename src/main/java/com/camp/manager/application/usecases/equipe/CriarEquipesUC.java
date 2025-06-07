package com.camp.manager.application.usecases.equipe;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.CronogramaEntityDomain;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.equipe.EquipeRequest;
import com.camp.manager.infra.http.request.equipe.EquipesRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CriarEquipesUC implements UseCase<EquipesRequest, MethodResponse<Void>> {

    private final EquipeGateway equipeGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public CriarEquipesUC(EquipeGateway equipeGateway,
                          AcampamentoGateway acampamentoGateway) {
        this.equipeGateway = equipeGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(EquipesRequest input) {
        if(input.equipes().isEmpty()) throw new IllegalArgumentException("A lista de equipes não pode ser nula ou vazia!");
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input.idAcampamento());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com id [" + input.idAcampamento() + "] não existe!");

        AcampamentoEntityDomain acampamentoEncontrado =
                this.acampamentoGateway.buscarAcampamentoPorId(input.idAcampamento());

        List<EquipeEntityDomain> equipesDeTrabalho = this.converterListaDeEquipeRequestParaEquipeDomain(input.equipes(), acampamentoEncontrado);

        this.equipeGateway.inserirTodosAsEquipesDeTrabalho(equipesDeTrabalho);

        return new MethodResponse<>(201, "Equipes adicionadas com sucesso!", null);
    }


    private List<EquipeEntityDomain> converterListaDeEquipeRequestParaEquipeDomain(List<EquipeRequest> listaDeEquipeRequest, AcampamentoEntityDomain acampamentoEncontrado) {
        List<EquipeEntityDomain> equipesDeTrabalho = new ArrayList<>();

        listaDeEquipeRequest.forEach(equipe -> {
            EquipeEntityDomain equipeCriada = new EquipeEntityDomain(
                    null,
                    equipe.nome(),
                    equipe.tipoEquipe(),
                    new CronogramaEntityDomain(
                            null,
                            LocalDateConverterAPP.converterStringParaLocalDate(equipe.cronograma().dataInicial()),
                            LocalDateConverterAPP.converterStringParaLocalDate(equipe.cronograma().dataFinal()),
                            equipe.cronograma().descricao()),
                    acampamentoEncontrado,
                    new ArrayList<>(),
                    new ArrayList<>(),
                    null
            );
            equipesDeTrabalho.add(equipeCriada);
        });

        return equipesDeTrabalho;
    }
}
