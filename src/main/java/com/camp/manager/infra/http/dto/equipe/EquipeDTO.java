package com.camp.manager.infra.http.dto.equipe;

import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.infra.http.dto.cronograma.CronogramaDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class EquipeDTO {

    private final Long id;
    private final String nome;
    private final String tipoEquipe;
    private final CronogramaDTO cronograma;

    public EquipeDTO(EquipeEntityDomain equipeEntityDomain) {
        this.id = equipeEntityDomain.id();
        this.nome = equipeEntityDomain.nome();
        this.tipoEquipe = equipeEntityDomain.tipoEquipe();
        this.cronograma = new CronogramaDTO(equipeEntityDomain.cronograma());
    }

    public static List<EquipeDTO> converter(List<EquipeEntityDomain> listEquipe) {
        return listEquipe.stream()
                .map(EquipeDTO::new)
                .toList();
    }

}
