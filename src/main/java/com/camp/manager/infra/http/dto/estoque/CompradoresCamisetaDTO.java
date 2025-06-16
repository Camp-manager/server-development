package com.camp.manager.infra.http.dto.estoque;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CompradoresCamisetaDTO {

    private Double totalComVendas;
    private Long totalCompradores;
    private Long quantidadeCompradoresCampistas;
    private Long QuantidadeCompradoresFuncionarios;

    List<CampistaUltraBasicoDTO> campistasCompradores;
    List<FuncionarioUltraBasicoDTO> funcionariosCompradores;

}
