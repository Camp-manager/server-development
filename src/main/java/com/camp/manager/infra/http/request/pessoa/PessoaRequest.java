package com.camp.manager.infra.http.request.pessoa;

import com.camp.manager.infra.http.request.endereco.EnderecoRequest;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import org.hibernate.validator.constraints.br.CPF;

public record PessoaRequest(
        @NotBlank(message = "Nome Completo não pode ser nulo ou em branco!")
        String nomeCompleto,

        @CPF(message = "CPF inválido!")
        @NotBlank(message = "CPF não pode ser nulo ou em branco!")
        String cpf,

        @NotBlank(message = "Data de Nascimento não pode ser nulo ou em branco!")
        String dataNascimento,

        @NotBlank(message = "Telefone não pode ser nulo ou em branco!")
        String telefone,

        @NotBlank(message = "Sexo não pode ser nulo ou em branco!")
        String sexo,

        @NotNull(message = "Peso não pode ser nulo!")
        @Positive(message = "Peso deve ser um valor positivo!")
        Double peso,

        @NotNull(message = "Altura não pode ser nulo!")
        @Positive(message = "Altura deve ser um valor positivo!")
        Double altura,

        String alimentoPredileto,

        @NotNull(message = "Foi Batizado não pode ser nulo!")
        Boolean foiBatizado,

        @NotNull(message = "Tem Primeira Comunhão não pode ser nulo!")
        Boolean temPrimeiraComunhao,

        EnderecoRequest endereco,
        FamiliarRequest familiar) {
}
