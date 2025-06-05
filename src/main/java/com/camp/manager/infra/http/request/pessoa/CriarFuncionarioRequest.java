package com.camp.manager.infra.http.request.pessoa;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

@Getter
public class CriarFuncionarioRequest{

        @NotBlank(message = "Nome não pode ser nulo ou em branco!")
        private String nome;

        @Email
        @NotBlank(message = "Email não pode ser nulo ou em branco!")
        private String email;

        @CPF
        @NotBlank(message = "CPF não pode ser nulo ou em branco!")
        private String cpf;

        @NotBlank(message = "Telefone não pode ser nulo ou em branco!")
        private String telefone;

        @NotBlank(message = "Habilidade não pode ser nulo ou em branco!")
        private String habilidade;

        private String tamanhoCamisa;

        @Setter
        private String codigoRegistro;
}
