package com.camp.manager.infra.http.dto.estoque;

import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class FuncionarioUltraBasicoDTO {

    private Long id;
    private String nome;
    private String tamanhoCamisetaComprado;

    private FuncionarioUltraBasicoDTO(FuncionarioEntityDomain funcionario) {
        this.id = funcionario.id();
        this.nome = funcionario.nome();
        this.tamanhoCamisetaComprado = funcionario.camiseta().tamanhoCamiseta();
    }

    public static List<FuncionarioUltraBasicoDTO> converter(List<FuncionarioEntityDomain> funcionarios) {
        return funcionarios.stream()
                .map(FuncionarioUltraBasicoDTO::new)
                .toList();
    }

}
