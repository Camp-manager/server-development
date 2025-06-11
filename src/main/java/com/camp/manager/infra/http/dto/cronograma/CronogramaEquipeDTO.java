package com.camp.manager.infra.http.dto.cronograma;

import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;
import com.camp.manager.utils.converter.localDate.LocalDateConverterAPP;
import lombok.Getter;

import java.util.List;

@Getter
public class CronogramaEquipeDTO {

    private final Long id;
    private final String dataInicio;
    private final String dataFinal;
    private final String descricao;
    private final Long equipeId;

    public CronogramaEquipeDTO(CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain) {
        this.id = cronogramaEquipeEntityDomain.id();
        this.dataInicio = LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataInicio());
        this.dataFinal = LocalDateConverterAPP.converterLocalDateParaString(cronogramaEquipeEntityDomain.dataFinal());
        this.descricao = cronogramaEquipeEntityDomain.descricao();
        this.equipeId = cronogramaEquipeEntityDomain.equipe().id();
    }

    public static List<CronogramaEquipeDTO> converter(List<CronogramaEquipeEntityDomain> listCronogramaEquipe) {
        return listCronogramaEquipe.stream()
                .map(CronogramaEquipeDTO::new)
                .toList();
    }
}
