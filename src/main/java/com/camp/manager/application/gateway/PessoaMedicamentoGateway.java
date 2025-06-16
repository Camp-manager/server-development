package com.camp.manager.application.gateway;

import com.camp.manager.domain.entity.MedicamentoEntityDomain;
import com.camp.manager.domain.entity.PessoaEntityDomain;
import com.camp.manager.domain.entity.PessoaMedicamentoEntityDomain;

import java.util.List;

public interface PessoaMedicamentoGateway {
    void salvarPessoasMedicamento(PessoaEntityDomain pessoaEntity, MedicamentoEntityDomain medicamentoEntity);
    List<PessoaMedicamentoEntityDomain> buscarMedicamentosPorPessoa(PessoaEntityDomain pessoaEntityDomain);
}
