package com.camp.manager.infra.http.dto.pessoa;

import com.camp.manager.domain.entity.FuncionarioEntityDomain;
import lombok.Getter;

import java.util.List;

@Getter
public class FuncionarioBasicoDTO {

    private final Long id;
    private final String nome;
    private final String telefone;
    private final String habilidade;

    public FuncionarioBasicoDTO(FuncionarioEntityDomain funcionario) {
        this.id = funcionario.id();
        this.nome = funcionario.nome();
        this.telefone = funcionario.telefone();
        this.habilidade = funcionario.habilidade();
    }

    public static List<FuncionarioBasicoDTO> converter(List<FuncionarioEntityDomain> listaDeFuncionarios) {
        return listaDeFuncionarios.stream().map(FuncionarioBasicoDTO::new).toList();
    }
}
