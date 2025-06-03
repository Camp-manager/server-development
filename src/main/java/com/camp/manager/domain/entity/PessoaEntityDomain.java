package com.camp.manager.domain.entity;

import java.time.LocalDate;

public record PessoaEntityDomain(
        Long id,
        String nome,
        String sobrenome,
        String cpf,
        LocalDate dataNascimento,
        String telefone,
        String sexo,
        Double peso,
        Double altura,
        String alimentoPredileto,
        Boolean foiBatizado,
        Boolean temPrimeiraComunhao,
        EnderecoEntityDomain endereco,
        FamiliarEntityDomain familiar) {
}
