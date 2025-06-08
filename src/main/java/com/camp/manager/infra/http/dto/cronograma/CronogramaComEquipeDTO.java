package com.camp.manager.infra.http.dto.cronograma;

import com.camp.manager.domain.entity.EquipeEntityDomain;
import lombok.Getter;

import java.util.List;

@Getter
public class CronogramaComEquipeDTO {
    private final String nomeEquipe;
    private final List<CronogramaDTO> cronogramas;

    public CronogramaComEquipeDTO(EquipeEntityDomain equipe) {
        this.nomeEquipe = equipe.nome();
        this.cronogramas = CronogramaDTO.converter(equipe.cronograma());
    }

    public static List<CronogramaComEquipeDTO> converter(List<EquipeEntityDomain> equipes) {
        return equipes.stream()
                .map(CronogramaComEquipeDTO::new)
                .toList();
    }
}
