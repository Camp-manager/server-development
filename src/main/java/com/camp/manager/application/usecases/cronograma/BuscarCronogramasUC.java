package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.AcampamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.dto.cronograma.CronogramaComEquipeDTO;
import com.camp.manager.infra.http.dto.cronograma.TodosCronogramaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuscarCronogramasUC implements UseCase<Long, MethodResponse<TodosCronogramaDTO>> {

    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public BuscarCronogramasUC(AcampamentoGateway acampamentoGateway) {
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    public MethodResponse<TodosCronogramaDTO> execute(Long input) {
        boolean acampamentoEhExistente = this.acampamentoGateway.acampamentoEhExistentePorId(input);
        if(!acampamentoEhExistente) throw new NotFoundException("Acampamento com o id: [" + input + "] não encontrado!");

        AcampamentoEntityDomain acampamentoEncontrado = this.acampamentoGateway.buscarAcampamentoPorId(input);

        List<CronogramaComEquipeDTO> cronogramaComPorEquipeTrabalho = new ArrayList<>();
        List<CronogramaComEquipeDTO> cronogramaComPorEquipeCampistas = new ArrayList<>();

        acampamentoEncontrado.equipesDoAcampamento().forEach(equipeEncontrado -> {
            if(equipeEncontrado.tipoEquipe().equals("Trabalho")) {
                cronogramaComPorEquipeTrabalho.add(new CronogramaComEquipeDTO(equipeEncontrado));
            } else if (equipeEncontrado.tipoEquipe().equals("Campista")) {
                cronogramaComPorEquipeCampistas.add(new CronogramaComEquipeDTO(equipeEncontrado));
            }
        });

        return new MethodResponse<>(200, "Cronogramas encontrados com sucesso!", new TodosCronogramaDTO(cronogramaComPorEquipeCampistas, cronogramaComPorEquipeTrabalho));
    }
}
