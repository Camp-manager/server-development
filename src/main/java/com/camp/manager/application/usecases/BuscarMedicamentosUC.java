package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.MedicamentoGateway;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.medicamento.MedicamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarMedicamentosUC implements UseCase<MethodResponse<Void>, MethodResponse<List<MedicamentoDTO>>>{

    private final MedicamentoGateway medicamentoGateway;

    @Autowired
    public BuscarMedicamentosUC(MedicamentoGateway medicamentoGateway) {
        this.medicamentoGateway = medicamentoGateway;
    }

    @Override
    public MethodResponse<List<MedicamentoDTO>> execute(MethodResponse<Void> input) {
        return MedicamentoDTO.converter(this.medicamentoGateway.buscarTodosMedicamentos());
    }
}
