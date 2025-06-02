package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.CronogramaEntityDomain;

public interface CronogramaGateway {
    void adicionarCronograma(CronogramaEntityDomain cronograma);
}
