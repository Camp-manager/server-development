package com.camp.manager.infra.http.dto.cronograma;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class TodosCronogramaDTO {
    List<CronogramaComEquipeDTO> cronogramasEquipeTrabalho;
    List<CronogramaComEquipeDTO> cronogramasEquipeCampistas;
}
