package com.camp.manager.infra.http.dto.galeria;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.nio.file.Path;

@Getter
public class ImagemDTO {
    private String nomeDoArquivo;
    private Path pathImagem;
    private String dataDeCriacao;
    private Double tamanhoDoArquivo;

    public ImagemDTO () {}
}
