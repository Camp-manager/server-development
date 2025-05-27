package com.camp.manager.application.usecases.tipoacampamento;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.enums.CategoriaTipoAcampamento;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscarCategoriasTipoAcampamentoUC implements UseCase<Void, List<String>> {

    @Override
    public List<String> execute(Void input) {
        return Arrays
                .stream(CategoriaTipoAcampamento.values())
                .map(CategoriaTipoAcampamento::getDescricao)
                .collect(Collectors.toList());
    }
}
