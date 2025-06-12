package com.camp.manager.application.usecases.pessoa;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.Parentesco;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BuscarParentescoUC implements UseCase<Void, MethodResponse<List<String>>> {

    @Override
    public MethodResponse<List<String>> execute(Void input) {
        List<String> parentesco = Arrays
                .stream(Parentesco.values())
                .map(Parentesco::getDescricao)
                .toList();
        return new MethodResponse<>(200, "Parentescos encontrados com sucesso!", parentesco);
    }
}
