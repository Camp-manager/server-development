package com.camp.manager.infra.http.dto.cronograma;

import lombok.Getter;

import java.util.List;

@Getter
public class TodosCronogramaDTO {
    List<CronogramaComEquipeDTO> cronogramasEquipeTrabalho;
    List<CronogramaComEquipeDTO> cronogramasEquipeCampistas;
}
