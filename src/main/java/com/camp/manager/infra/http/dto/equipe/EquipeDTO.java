package com.camp.manager.infra.http.dto.equipe;

import com.camp.manager.domain.entity.EquipeEntityDomain;
import com.camp.manager.infra.http.dto.cronograma.CronogramaComEquipeDTO;
import com.camp.manager.infra.http.dto.cronograma.CronogramaDTO;
import com.camp.manager.infra.http.dto.cronograma.CronogramaEquipeDTO;
import com.camp.manager.infra.http.dto.pessoa.CampistaBasicoDTO;
import com.camp.manager.infra.http.dto.pessoa.FuncionarioBasicoDTO;
import lombok.Getter;

import java.util.List;

@Getter
public class EquipeDTO {

    private final Long id;
    private final String nome;
    private final String tipoEquipe;
    private final List<CronogramaEquipeDTO> cronogramas;
    private final List<CampistaBasicoDTO> campistasNaEquipe;
    private final List<FuncionarioBasicoDTO> funcionariosNaEquipe;

    public EquipeDTO(EquipeEntityDomain equipeEntityDomain) {
        this.id = equipeEntityDomain.id();
        this.nome = equipeEntityDomain.nome();
        this.tipoEquipe = equipeEntityDomain.tipoEquipe();
        this.cronogramas = CronogramaEquipeDTO.converter(equipeEntityDomain.cronograma());
        this.campistasNaEquipe = CampistaBasicoDTO.converter(equipeEntityDomain.campistasNaEquipe());
        this.funcionariosNaEquipe = FuncionarioBasicoDTO.converter(equipeEntityDomain.funcionariosNaEquipe());
    }

    public static List<EquipeDTO> converter(List<EquipeEntityDomain> listEquipe) {
        return listEquipe.stream()
                .map(EquipeDTO::new)
                .toList();
    }
}
