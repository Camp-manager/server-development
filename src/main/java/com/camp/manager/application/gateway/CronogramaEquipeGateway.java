package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.CronogramaEquipeEntityDomain;

public interface CronogramaEquipeGateway {
    CronogramaEquipeEntityDomain salvarCronogramaEquipe(CronogramaEquipeEntityDomain cronogramaEquipeEntityDomain);
}
