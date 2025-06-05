package com.camp.manager.infra.http.request.pessoa;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
public class CriarCampistaRequest{

    Boolean usaMedicamento;
    Boolean temAlergia;
    List<String> alergias;
    Boolean jaFezAcampamento;
    List<String> acampamentosFeitos;
    Boolean temBarraca;
    PessoaRequest pessoa;


    @Setter
    private String codigoRegistro;

}
