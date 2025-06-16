package com.camp.manager.domain.entity;

public record PessoaMedicamentoEntityDomain(
        Long id,
        PessoaEntityDomain pessoa,
        MedicamentoEntityDomain medicamento) {
}
