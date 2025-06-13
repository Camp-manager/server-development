package com.camp.manager.infra.http.request.cronograma;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class CriarCronogramaTrabalhoRequest {
    private Long idDoCampamento;
    private List<Long> idsDasEquipes;
    private List<CronogramaRequest> cronogramasParaAEquipe;
}
