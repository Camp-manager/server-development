package com.camp.manager.infra.http.dto.buscarGaleriasUC;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ImagemDTO {
    private String nomeDoArquivo;
    private byte[] arquivoImagem;
    private String dataDeCriacao;
    private Double tamanhoDoArquivo;
}
