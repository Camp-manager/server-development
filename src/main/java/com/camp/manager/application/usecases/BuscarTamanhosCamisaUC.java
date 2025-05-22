package com.camp.manager.application.usecases;

import com.camp.manager.domain.enums.TamanhoCamiseta;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarTamanhosCamisaUC implements UseCase<Void, List<String>> {

    @Override
    public List<String> execute(Void input) {
        return Arrays
                .stream(TamanhoCamiseta.values())
                .map(TamanhoCamiseta::getValor)
                .collect(Collectors.toList());
    }
}
