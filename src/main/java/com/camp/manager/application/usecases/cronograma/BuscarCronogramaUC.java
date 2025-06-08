package com.camp.manager.application.usecases.cronograma;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.cronograma.CronogramaDTO;
import org.springframework.stereotype.Service;

@Service
public class BuscarCronogramaUC implements UseCase<Void, MethodResponse<CronogramaDTO>> {
    @Override
    public MethodResponse<CronogramaDTO> execute(Void input) {
        return null;
    }
}
