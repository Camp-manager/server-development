package com.camp.manager.infra.http.dto.cronograma;

import com.camp.manager.domain.entity.CronogramaEntityDomain;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class CronogramaDTO {

    private final Long id;
    private final LocalDate dataInicio;
    private final LocalDate dataFinal;
    private final String descricao;

    public CronogramaDTO(CronogramaEntityDomain cronogramaEntityDomain) {
        this.id = cronogramaEntityDomain.id();
        this.dataInicio = cronogramaEntityDomain.dataInicio();
        this.dataFinal = cronogramaEntityDomain.dataFinal();
        this.descricao = cronogramaEntityDomain.descricao();
    }
}
