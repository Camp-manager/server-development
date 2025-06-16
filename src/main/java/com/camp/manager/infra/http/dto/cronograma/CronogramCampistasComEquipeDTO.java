package com.camp.manager.infra.http.dto.cronograma;

import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.infra.http.dto.equipe.EquipeDiaFuncaoDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class CronogramCampistasComEquipeDTO {
    private final String nomeEquipe;
    private final List<EquipeDiaFuncaoDTO> cronogramaDaEquipeCampista;

    public CronogramCampistasComEquipeDTO(EquipeEntityDomain equipe) {
        this.nomeEquipe = equipe.nome();
        this.cronogramaDaEquipeCampista = EquipeDiaFuncaoDTO.converter(equipe.diasDaFuncao());
    }
}
