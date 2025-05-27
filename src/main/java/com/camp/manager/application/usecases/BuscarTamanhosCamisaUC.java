package com.camp.manager.application.usecases;

import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.TamanhoCamiseta;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarTamanhosCamisaUC implements UseCase<Void, MethodResponse<List<String>>> {

    @Override
    public MethodResponse<List<String>> execute(Void input) {
        List<String> tamanhos = Arrays
                .stream(TamanhoCamiseta.values())
                .map(TamanhoCamiseta::getValor)
                .toList();
        return new MethodResponse<>(200, "Tamanhos de camisa encontrados com sucesso!", tamanhos);
    }
}
