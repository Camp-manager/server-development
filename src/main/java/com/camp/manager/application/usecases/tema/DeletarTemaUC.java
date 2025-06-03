package com.camp.manager.application.usecases.tema;

import com.camp.manager.application.gateway.TemaGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.exception.custom.NotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarTemaUC implements UseCase<Long, MethodResponse<Void>> {

    private final TemaGateway temaGateway;

    @Autowired
    public DeletarTemaUC(TemaGateway temaGateway) {
        this.temaGateway = temaGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(Long input) {
        if(input == null) throw new NotFoundException("O ID do tema não pode ser nulo!");

        this.temaGateway.deletarTemaPorId(input);
        return new MethodResponse<>(204, "Tema deletado com sucesso!", null);
    }
}
