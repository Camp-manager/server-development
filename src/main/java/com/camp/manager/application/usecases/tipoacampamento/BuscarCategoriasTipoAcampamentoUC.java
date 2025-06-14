package com.camp.manager.application.usecases.tipoacampamento;

import com.camp.manager.application.usecases.UseCase;
import com.camp.manager.domain.entity.utils.MethodResponse;
import com.camp.manager.domain.enums.CategoriaTipoAcampamento;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class BuscarCategoriasTipoAcampamentoUC implements UseCase<Void, MethodResponse<List<String>>> {

    @Override
    @Transactional
    public MethodResponse<List<String>> execute(Void input) {
        List<String> categorias = Arrays
                .stream(CategoriaTipoAcampamento.values())
                .map(CategoriaTipoAcampamento::getDescricao)
                .toList();;
        return new MethodResponse<>(200, "Categorias de tipo de acampamento encontradas com sucesso!", categorias);
    }
}
