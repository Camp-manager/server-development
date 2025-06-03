package com.camp.manager.application.usecases.tipoacampamento;

import com.camp.manager.application.gateway.TipoAcampamentoGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeletarTipoAcampanentoUC implements UseCase<Long, MethodResponse<Void>> {

    private final TipoAcampamentoGateway tipoAcampamentoGateway;

    @Autowired
    public DeletarTipoAcampanentoUC(TipoAcampamentoGateway tipoAcampamentoGateway) {
        this.tipoAcampamentoGateway = tipoAcampamentoGateway;
    }

    @Override
    @Transactional
    public MethodResponse<Void> execute(Long input) {
        boolean tipoAcampamentoExiste = this.tipoAcampamentoGateway.tipoAcampamentoEhExistentePorId(input);
        if(!tipoAcampamentoExiste) throw new EntityNotFoundException("Tipo de acampamento com id [" + input + "] não encontrado.");

        this.tipoAcampamentoGateway.deletarTipoAcampamentoPorId(input);
        return new MethodResponse<>(204, "Tipo de acampamento deletado com sucesso!", null);
    }
}
