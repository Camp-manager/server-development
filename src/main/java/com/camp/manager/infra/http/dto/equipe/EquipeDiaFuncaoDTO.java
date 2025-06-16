package com.camp.manager.infra.http.dto.equipe;

import com.camp.manager.domain.entity.EquipeDiaFuncaoEntityDomain;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class EquipeDiaFuncaoDTO {
    private final String funcao;
    private final String data;

    public EquipeDiaFuncaoDTO(EquipeDiaFuncaoEntityDomain domain) {
        this.funcao = domain.funcao();
        this.data = domain.data();
    }

    public static List<EquipeDiaFuncaoDTO> converter(List<EquipeDiaFuncaoEntityDomain> funcoes) {
        if (funcoes == null) {
            return List.of();
        }
        return funcoes.stream()
                .map(EquipeDiaFuncaoDTO::new)
                .collect(Collectors.toList());
    }
}
