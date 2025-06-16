package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.gateway.AcampamentoGateway;
import com.camp.manager.application.gateway.EquipeDiaFuncaoGateway;
import com.camp.manager.application.gateway.EquipeGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.EquipeDiaFuncaoEntityDomain;
import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import com.camp.manager.infra.http.request.cronograma.CriarCronogramCampistasRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdicionarCronogramaCampistasUC implements UseCase<CriarCronogramCampistasRequest, MethodResponse<Void>> {

    private final EquipeDiaFuncaoGateway equipeDiaFuncaoGateway;
    private final EquipeGateway equipeGateway;
    private final AcampamentoGateway acampamentoGateway;

    @Autowired
    public AdicionarCronogramaCampistasUC(EquipeDiaFuncaoGateway equipeDiaFuncaoGateway,
                                          EquipeGateway equipeGateway,
                                          AcampamentoGateway acampamentoGateway) {
        this.equipeDiaFuncaoGateway = equipeDiaFuncaoGateway;
        this.equipeGateway = equipeGateway;
        this.acampamentoGateway = acampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(CriarCronogramCampistasRequest input) {
        boolean acampamentoExiste = this.acampamentoGateway.acampamentoEhExistentePorId(input.getIdDoCampamento());
        if(!acampamentoExiste) throw new NotFoundException("O acampamento com id [" + input.getIdDoCampamento() + "] não existe!");

        this.validarIdsDeEquipes(input.getIdsDasEquipes());

        List<EquipeDiaFuncaoEntityDomain> diasDeFuncoesDasEquipes = new ArrayList<>();

        input.getIdsDasEquipes().forEach(idEquipe -> input.getEquipesDiaFuncaoRequests().forEach(equipeDiaFuncaoRequest ->{
            EquipeEntityDomain equipeEncontrada = this.equipeGateway.buscarEquipePorId(idEquipe);

            EquipeDiaFuncaoEntityDomain equipeDiaFuncaoNovo = new EquipeDiaFuncaoEntityDomain(
                    null,
                    equipeDiaFuncaoRequest.funcao(),
                    equipeDiaFuncaoRequest.data(),
                    equipeEncontrada
            );

            diasDeFuncoesDasEquipes.add(equipeDiaFuncaoNovo);
        }));

        this.equipeDiaFuncaoGateway.salvarTodosEquipeDiaFuncao(diasDeFuncoesDasEquipes);

        return new MethodResponse<>(201, "Cronogramas adicionados para campistas com sucesso", null);
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
