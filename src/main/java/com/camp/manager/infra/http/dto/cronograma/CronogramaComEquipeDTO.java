package com.camp.manager.infra.http.dto.cronograma;

import lombok.Getter;

import java.util.List;

@Getter
public class CronogramaComEquipeDTO {
    private String nomeEquipe;
    private List<CronogramaDTO> cronogramas;
}
