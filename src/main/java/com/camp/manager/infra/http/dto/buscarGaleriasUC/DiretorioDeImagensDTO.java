package com.camp.manager.infra.http.dto.buscarGaleriasUC;

import lombok.Getter;

import java.util.List;

@Getter
public class DiretorioDeImagensDTO extends ImagemDTO {
    private final String nomeDoAcampamento;
    private final List<ImagemDTO> imagensDoAcampamento;

    public DiretorioDeImagensDTO(String nomeDoArquivo, byte[] arquivoImagem, String dataDeCriacao, Double tamanhoDoArquivo, String nomeDoAcampamento, List<ImagemDTO> imagensDoAcampamento) {
        super(nomeDoArquivo, arquivoImagem, dataDeCriacao, tamanhoDoArquivo);
        this.nomeDoAcampamento = nomeDoAcampamento;
        this.imagensDoAcampamento = imagensDoAcampamento;
    }
}
