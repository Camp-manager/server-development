package com.camp.manager.application.usecases.estoque;

import com.camp.manager.application.gateway.EstoqueGateway;
import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.infra.http.dto.estoque.EstoqueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarItensPorEstoqueUC implements UseCase<Void, MethodResponse<List<EstoqueDTO>>> {

    private final EstoqueGateway estoqueGateway;

    @Autowired
    public BuscarItensPorEstoqueUC(EstoqueGateway estoqueGateway) {
        this.estoqueGateway = estoqueGateway;
    }

    @Override
    public MethodResponse<List<EstoqueDTO>> execute(Void input) {
        List<EstoqueDTO> estoques = EstoqueDTO.converter(this.estoqueGateway.buscarTodosOsEstoques());
        return new MethodResponse<>(200, "Busca realizada com sucesso", estoques);
    }
}
