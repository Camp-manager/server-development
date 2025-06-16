package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.AtividadeGateway;
import com.camp.manager.application.gateway.CronogramaEquipeGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AtividadeEntityDomain;
import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.cronograma.CriarCronogramaTrabalhoRequest;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdicionarCronogramaTrabalhoUC implements UseCase<CriarCronogramaTrabalhoRequest, MethodResponse<Void>> {

    private final CronogramaEquipeGateway cronogramaEquipeGateway;
    private final EquipeGateway equipeGateway;
    private final AtividadeGateway atividadeGateway;

    @Autowired
    public AdicionarCronogramaTrabalhoUC(CronogramaEquipeGateway cronogramaEquipeGateway,
                                         EquipeGateway equipeGateway, AtividadeGateway atividadeGateway) {
        this.cronogramaEquipeGateway = cronogramaEquipeGateway;
        this.equipeGateway = equipeGateway;
        this.atividadeGateway = atividadeGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(CriarCronogramaTrabalhoRequest input) {
        validarIdsDeEquipes(input.getIdsDasEquipes());

        input.getIdsDasEquipes().forEach(idEquipe -> {
            EquipeEntityDomain equipeEncontrada = equipeGateway.buscarEquipePorId(idEquipe);

            input.getCronogramasParaAEquipe().forEach(cronogramaRequest -> {

                CronogramaEquipeEntityDomain cronogramaPaiComFilhos = new CronogramaEquipeEntityDomain(
                        null,
                        LocalDateConverterAPP.converterStringParaLocalDate(cronogramaRequest.dataInicial()),
                        LocalDateConverterAPP.converterStringParaLocalDate(cronogramaRequest.dataFinal()),
                        cronogramaRequest.descricao(),
                        equipeEncontrada,
                        new ArrayList<>()
                );

                CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain = cronogramaEquipeGateway.salvarCronogramaEquipe(cronogramaPaiComFilhos);

                List<AtividadeEntityDomain> atividades = cronogramaRequest.atividades().stream()
                        .map(req -> new AtividadeEntityDomain(
                                null,
                                req.tipo(),
                                LocalTime.parse(req.horario()),
                                req.descricao(),
                                cronogramaEquipeEntityDomain
                        )).collect(Collectors.toList());

                this.atividadeGateway.salvarTodasAtividades(atividades);
            });
        });

        return new MethodResponse<>(201, "Cronogramas e atividades foram salvos com sucesso.", null);
    }

    private void validarIdsDeEquipes(List<Long> idsDasEquipes) {
        if (idsDasEquipes == null || idsDasEquipes.isEmpty()) {
            throw new NotFoundException("Nenhuma equipe informada para o cronograma!");
        }
        idsDasEquipes.forEach(id -> {
            boolean equipeExiste = this.equipeGateway.equipeEhExistentePorId(id);
            if (!equipeExiste) {
                throw new NotFoundException("A equipe com id [" + id + "] não existe!");
            }
        });
    }
}