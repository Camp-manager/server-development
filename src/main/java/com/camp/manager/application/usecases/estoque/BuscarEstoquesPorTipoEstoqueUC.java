package com.camp.manager.application.usecases.estoque;

import com.camp.manager.application.gateway.EstoqueGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.EstoqueEntityDomain;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.estoque.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarEstoquesPorTipoEstoqueUC implements UseCase<String, MethodResponse<List<EstoqueDTO>>> {

    private final EstoqueGateway estoqueGateway;

    @Autowired
    public BuscarEstoquesPorTipoEstoqueUC(EstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Override
    public MethodResponse<List<EstoqueDTO>> execute(String input) {
        List<EstoqueEntityDomain> estoques = this.estoqueGateway.buscarEstoquesPorTipoEstoque(input);

        return new MethodResponse<>(200, "Estoques encontrados com sucesso!", EstoqueDTO.converter(estoques) );
    }
}
