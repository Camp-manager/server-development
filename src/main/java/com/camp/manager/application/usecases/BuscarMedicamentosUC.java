package com.camp.manager.application.usecases;

import com.camp.manager.application.gateway.MedicamentoGateway;
import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.medicamento.MedicamentoDTO;
import jakarta.transaction.Transactional;
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
    @Transactional
    public MethodResponse<List<MedicamentoDTO>> execute(MethodResponse<Void> input) {
        List<MedicamentoEntityDomain> medicamentos = this.medicamentoGateway.buscarTodosMedicamentos();
        return new MethodResponse<>(200, "Medicamentos encontrados com sucesso!", MedicamentoDTO.converter(medicamentos));
    }
}
