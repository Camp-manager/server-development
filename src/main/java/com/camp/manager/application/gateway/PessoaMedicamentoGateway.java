package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.domain.entity.PessoaEntityDomain;

public interface PessoaMedicamentoGateway {
    void salvarPessoasMedicamento(PessoaEntityDomain pessoaEntity, MedicamentoEntityDomain medicamentoEntity);
}
