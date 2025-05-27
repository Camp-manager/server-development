package com.camp.manager.infra.http.dto.galeria;

import lombok.Getter;

import java.util.List;

@Getter
public class DiretorioDeImagensDTO  {
    private final String nomeDoAcampamento;
    private final byte[] imagemDeCapa;
    private final List<ImagemDTO> imagensDoAcampamento;

    public DiretorioDeImagensDTO(String nomeDoAcampamento, byte[] imagemDeCapa, List<ImagemDTO> imagensDoAcampamento) {
        this.nomeDoAcampamento = nomeDoAcampamento;
        this.imagemDeCapa = imagemDeCapa;
        this.imagensDoAcampamento = imagensDoAcampamento;
    }
}
