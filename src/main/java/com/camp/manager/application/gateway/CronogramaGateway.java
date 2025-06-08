package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.CronogramaEntityDomain;

import java.util.List;

public interface CronogramaGateway {
    void adicionarCronograma(CronogramaEntityDomain cronograma);
    CronogramaEntityDomain buscarCronogramaPorId(Long id);
    boolean cronogramaEhExistente(Long idCronograma);
    List<CronogramaEntityDomain> buscarTodosCronogramasPorAcampamento();
}
