package com.camp.manager.application.usecases.estoque;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.LocalEstoque;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BuscarTipoEstoqueUC implements UseCase<Void, MethodResponse<List<String>>> {

    @Override
    public MethodResponse<List<String>> execute(Void input) {
        List<String> localEstoque = Arrays
                .stream(LocalEstoque.values())
                .map(LocalEstoque::getDescricao)
                .toList();
        return new MethodResponse<>(200, "Parentescos encontrados com sucesso!", localEstoque);
    }

}
