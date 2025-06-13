package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.*;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AtividadeEntityDomain;
import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.cronograma.CriarCronogramaTrabalhoRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdicionarCronogramaTrabalhoUC implements UseCase<CriarCronogramaTrabalhoRequest, MethodResponse<Void>> {

    private final CronogramaEquipeGateway cronogramaEquipeGateway;
    private final AtividadeGateway atividadeGateway;
    private final AcampamentoGateway acampamentoGateway;
    private final EquipeGateway equipeGateway;

    @Autowired
    public AdicionarCronogramaTrabalhoUC(CronogramaEquipeGateway cronogramaEquipeGateway,
                                         AtividadeGateway atividadeGateway,
                                         AcampamentoGateway acampamentoGateway,
                                         EquipeGateway equipeGateway) {
        this.cronogramaEquipeGateway = cronogramaEquipeGateway;
        this.atividadeGateway = atividadeGateway;
        this.acampamentoGateway = acampamentoGateway;
        this.equipeGateway = equipeGateway;
    }

    @Override
    public MethodResponse<Void> execute(CriarCronogramaTrabalhoRequest input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input.getIdDoCampamento());
        if(!acampamentoEhExistente) throw new NotFoundException("O acampamento com id [" + input.getIdDoCampamento() + "] não existe!");

        this.validarIdsDeEquipes(input.getIdsDasEquipes());

        List<AtividadeEntityDomain> atividadesParaSerSalva = new ArrayList<>();

        input.getIdsDasEquipes().forEach(idEquipe -> input.getCronogramasParaAEquipe().forEach(cronograma -> {
            CronogramaEquipeEntityDomain cronogramaEquipeNovo = new CronogramaEquipeEntityDomain(
                    null,
                    LocalDateConverterAPP.converterStringParaLocalDate(cronograma.dataInicial()),
                    LocalDateConverterAPP.converterStringParaLocalDate(cronograma.dataFinal()),
                    cronograma.descricao(),
                    idEquipe
            );

            CronogramaEquipeEntityDomain cronogramaEquipeSalvo = this.cronogramaEquipeGateway.salvarCronogramaEquipe(cronogramaEquipeNovo);

            cronograma.atividades().forEach(atividade -> {
                AtividadeEntityDomain atividadeEntityDomain = new AtividadeEntityDomain(
                        null,
                        atividade.tipo(),
                        LocalTime.parse(atividade.horario()),
                        atividade.descricao(),
                        cronogramaEquipeSalvo
                );

                atividadesParaSerSalva.add(atividadeEntityDomain);
            });
        }));

        if(!atividadesParaSerSalva.isEmpty()) {
            this.atividadeGateway.salvarTodasAtividades(atividadesParaSerSalva);
        }

        return new MethodResponse<>(201, "Cronogramas adicionados para o trabalho com sucesso", null);
    }

    private void validarIdsDeEquipes(List<Long> idsDasEquipes) {
        if(idsDasEquipes == null || idsDasEquipes.isEmpty()) {
            throw new NotFoundException("Nenhuma equipe informada para o cronograma!");
        }

        idsDasEquipes.forEach(id -> {
            boolean equipeExiste = this.equipeGateway.equipeEhExistentePorId(id);
            if(!equipeExiste) {
                throw new NotFoundException("A equipe com id [" + id + "] não existe!");
            }
        });
    }
}
