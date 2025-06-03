package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.MedicamentoEntityDomain;

import java.util.List;

public interface MedicamentoGateway {
    List<MedicamentoEntityDomain> buscarTodosMedicamentos();
}
